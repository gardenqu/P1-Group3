pipeline {
    agent any

    stages {

        stage('Test Jenkins') {
            steps {
                echo 'Jenkins is working!'
                sh 'whoami'
                sh 'docker --version'
                sh 'docker compose version'
                sh 'docker buildx version'
            }
        }

        stage('Java Unit Tests') {
            steps {
                sh '''
                    docker run --rm \
                      -v "$WORKSPACE/manager_app:/app" \
                      -w /app \
                      maven:3.9-eclipse-temurin-17 \
                      mvn test
                '''
            }
        }

        stage('Python Unit Tests') {
            steps {
                sh '''
                    docker run --rm \
                      -v "$WORKSPACE/employee_app:/app" \
                      -w /app \
                      python:3.12 \
                      sh -c "
                        pip install -r requirements.txt &&
                        pytest tests
                      "
                '''
            }
        }

        stage('Build Docker Images') {
            steps {
                sh '''
                    docker compose build
                '''
            }
        }

        stage('Start Applications') {
            steps {
                sh '''
                    docker compose up -d
                '''
            }
        }

        stage('Wait for Applications') {
            steps {
                sh '''
                    echo "Waiting for applications to start..."
                    sleep 15
                    docker compose ps
                '''
            }
        }

        stage('API Tests') {
            steps {
                echo 'API tests will run here'
            }
        }

        stage('E2E Tests') {
            steps {
                echo 'E2E tests will run here'
            }
        }
    }

    post {
        always {
            sh '''
                docker compose down || true
            '''
        }
    }
}
