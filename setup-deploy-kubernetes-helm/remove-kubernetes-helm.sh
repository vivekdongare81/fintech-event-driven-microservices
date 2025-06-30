#!/bin/bash

# Namespace used
NAMESPACE="default"

echo "🔻 Stopping all microservices in namespace: $NAMESPACE"

helm uninstall kafka -n "$NAMESPACE"
helm uninstall grafana -n "$NAMESPACE"
helm uninstall grafana-loki -n "$NAMESPACE"
helm uninstall grafana-tempo -n "$NAMESPACE"
helm uninstall keycloak -n "$NAMESPACE"
helm uninstall kube-prometheus -n "$NAMESPACE"
# helm uninstall fintech-services -n "$NAMESPACE" # uncomment if needed

echo "✅ All Helm releases uninstalled from $NAMESPACE."

# Give it a few seconds to ensure resources are terminated
sleep 10

echo "🧹 Cleaning up PVCs in $NAMESPACE..."

kubectl delete pvc --all -n "$NAMESPACE"

echo "✅ Cleanup complete."

