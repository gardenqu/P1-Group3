pipeline {
    agent any

    stages {

        stage('Test Jenkins') {
            steps {
                echo 'Jenkins is working!'
                sh 'whoami'
                sh 'docker --version'
                sh 'docker compose version'
            }
        }

        stage('Java Unit Tests') {
            steps {
                sh '''
                    cd manager_app
                    mvn test
                '''
            }
        }

        stage('Python Unit Tests') {
            steps {
                sh '''
                    cd employee_app
                    pytest
                '''
            }
        }

        stage('Build Docker Images') {
            steps {
                sh 'docker compose build'
            }
        }

        stage('Start Application') {
            steps {
                sh 'docker compose up -d'
            }
        }

        stage('Wait for Applications') {
            steps {
                sh '''
                    echo "Waiting for applications to start..."
                    sleep 10
                    docker compose ps
                '''
            }
        }

        stage('API Tests') {
            steps {
                sh '''
                    echo "Running API tests..."

                    cd manager_app
                    mvn test

                    cd ../employee_app
                    pytest
                '''
            }
        }

        stage('E2E Tests') {
            steps {
                sh '''
                    echo "Running E2E tests..."

                    cd manager_app
                    mvn test

                    cd ../employee_app
                    pytest
                '''
            }
        }

    }

    post {
        always {
            sh 'docker compose ps || true'
        }
    }
}
