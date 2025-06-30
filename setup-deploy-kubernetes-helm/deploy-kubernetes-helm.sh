#!/bin/bash

set -e  # Stop script on any error

echo "🚀 Starting full deployment..."

# 0. Build dependencies in top-level environment chart
echo "🔄 Building Helm dependencies for dev-env..."
helm dependency build ./environments/dev-env

# 1. Optional: Install common library chart (only if you need to test/use it separately)
# ⚠️ If it's only used as a dependency and not deployed directly, skip this.
# echo "🔧 Installing devsoncall-common..."
# helm upgrade --install devsoncall-common ./devsoncall-common

# 2. Core infrastructure components (uncomment as needed)
for component in kafka grafana grafana-loki grafana-tempo keycloak kube-prometheus; do
  echo "🔧 Installing infra component: $component..."
  helm upgrade --install $component ./$component
done

# 3. Deploy full environment using top-level umbrella chart
#echo "🌍 Installing dev-env custom microservices environment and all dependencies..."
#helm upgrade --install dev-env ./environments/dev-env

echo "✅ All components and services installed successfully!"

