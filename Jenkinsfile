pipeline {

    agent any

    stages {

        stage('Test Jenkins') {
            steps {
                echo 'Jenkins is working!'
            }
        }

        stage('Build Docker Images') {
            steps {
                dockerCompose(
                    projectName: 'group3',
                    composeFile: 'docker-compose.yaml',
                    action: 'build'
                )
            }
        }

        stage('Java Unit Tests') {
            steps {
                dockerCompose(
                    projectName: 'group3-tests',
                    composeFile: 'docker-compose.test.yaml',
                    services: ['java-unit-tests'],
                    action: 'run'
                )
            }
        }

        stage('Python Unit Tests') {
            steps {
                dockerCompose(
                    projectName: 'group3-tests',
                    composeFile: 'docker-compose.test.yaml',
                    services: ['python-tests'],
                    action: 'run'
                )
            }
        }

        stage('Start Applications') {
            steps {
                dockerCompose(
                    projectName: 'group3',
                    composeFile: 'docker-compose.yaml',
                    action: 'up'
                )
            }
        }

        stage('Java API Tests') {
            steps {
                dockerCompose(
                    projectName: 'group3-tests',
                    composeFile: 'docker-compose.test.yaml',
                    services: ['java-api-tests'],
                    action: 'run'
                )
            }
        }

        stage('Java E2E Tests') {
            steps {
                dockerCompose(
                    projectName: 'group3-tests',
                    composeFile: 'docker-compose.test.yaml',
                    services: ['java-e2e-tests'],
                    action: 'run'
                )
            }
        }

        stage('Python E2E Tests') {
            steps {
                dockerCompose(
                    projectName: 'group3-tests',
                    composeFile: 'docker-compose.test.yaml',
                    services: ['python-e2e-tests'],
                    action: 'run'
                )
            }
        }
    }

    post {
        always {
            dockerCompose(
                projectName: 'group3',
                composeFile: 'docker-compose.yaml',
                action: 'down'
            )

            dockerCompose(
                projectName: 'group3-tests',
                composeFile: 'docker-compose.test.yaml',
                action: 'down'
            )
        }
    }
}
