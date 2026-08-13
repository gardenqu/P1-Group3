pipeline {
    agent any

    tools {
        maven 'Maven-3.9'
    }

    stages {

        stage('Test Jenkins') {
            steps {
                echo 'Jenkins is working!'
            }
        }

        stage('Java Unit Tests') {
            steps {
                dir('manager_app') {
                    sh 'mvn test -Punit'
                }
            }
        }

        stage('Python Unit Tests') {
    steps {
        dir('employee_app') {
            sh '''
                python3 -m venv .venv
                .venv/bin/pip install -r requirements.txt
                .venv/bin/pytest
            '''
        }
    }
}

        stage('Build Java Application') {
            steps {
                dir('manager_app') {
                    sh 'mvn package -DskipTests'
                }
            }
        }

        stage('Build Docker Images') {
            steps {
                sh 'docker compose build'
            }
        }

        stage('Start Applications') {
            steps {
                sh 'docker compose up -d'
            }
        }

        stage('Java API Tests') {
            steps {
                dir('manager_app') {
                    sh 'mvn test -Papi'
                }
            }
        }

        stage('Java E2E Tests') {
            steps {
                dir('manager_app') {
                    sh 'mvn test -Pe2e'
                }
            }
        }
    }

    post {
        always {
            sh 'docker compose down'
        }
    }
}
