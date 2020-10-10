pipeline {
  environment {
    registry = "denimallel/prod-cons-api"
  }
  agent any
  stages {
    stage('Build dockerfile') {
      steps {
        script {
          docker.build registry + ":latest"
        }
      }
    }
  }
}
