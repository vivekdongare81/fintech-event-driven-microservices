#!/bin/bash

set -e  # Stop script on any error

# Define release names and chart paths
echo "Starting full deployment..."

# Common components
echo "Installing devsoncall-common..."
helm upgrade --install devsoncall-common ./devsoncall-common

# Infrastructure tools
for component in kafka grafana grafana-loki grafana-tempo keycloak kube-prometheus; do
  echo "Installing $component..."
  helm upgrade --install $component ./$component
done

# Devsoncall services
services=(
  accounts
  cards
  configserver
  eurekaserver
  gatewayserver
  loans
  messagingserver
)

for service in "${services[@]}"; do
  echo "Installing service: $service..."
  helm upgrade --install $service ./devsoncall-services/$service
done

# Environments - dev-env qa-env prod-env
for env in dev-env; do
  echo "Installing environment: $env..."
  helm upgrade --install $env ./environments/$env
done

echo "✅ All components and services installed successfully!"
