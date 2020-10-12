pipeline {
    environment {
        registry = "denimallel/prod-cons-api"
        registryCredential = 'dockerhub_id'
        dockerImage = ''
    }
    agent any
    stages {
        stage('Build dockerfile') {
            steps {
                script {
                    dockerImage = docker.build registry + ":$BUILD_NUMBER"
                }
            }
        }
        stage('Push to Dockerhub') {
            steps {
                script {
                    docker.withRegistry('', registryCredential) {
                        dockerImage.push()
                    }
                }
            }
        }
        stage('Trigger Deploy') {
            steps {
                echo "Triggering API deploy"
                build job: 'prod-consumer-backend-deploy', parameters: [string(name: 'DOCKER_IMAGE', value: registryCredential)]
            }
        }
    }
}
