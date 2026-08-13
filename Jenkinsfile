pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out deployment branch...'
                checkout scm
            }
        }
        stage('Unit Tests') {
            steps {
                echo 'Running manager_app unit tests...'
                dir('manager_app') {
                    sh 'mvn test -Dtest=**/api/**,**/app/**,**/controller/**,**/dao/**'
                }
            }
            post {
                always {
                    junit 'manager_app/target/surefire-reports/*.xml'
                }
            }
        }
        stage('Build Docker Images') {
            steps {
                sh 'docker compose build'
            }
        }
        stage('Deploy for E2E') {
            steps {
                sh 'docker compose up -d'
            }
        }
        stage('E2E / JMeter Tests') {
            steps {
                echo 'Running manager_app e2e and jmeter tests...'
                dir('manager_app') {
                    sh 'mvn test -Dtest=**/e2e/**,**/jmeter/**'
                }
            }
            post {
                always {
                    junit 'manager_app/target/surefire-reports/*.xml'
                    sh 'docker compose down'
                }
            }
        }
    }
    post {
        failure {
            sh 'docker compose down || true'
        }
    }
}
