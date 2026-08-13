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
                sh 'docker compose build'
            }
        }

        stage('Start Applications') {
            steps {
                sh 'docker compose up -d'
            }
        }

        stage('Python Unit Tests') {
            steps {
                sh 'docker compose exec -T employee_app pytest'
            }
        }

       stage('Java Unit Tests') {
    steps {
        sh 'docker build --target test ./manager_app'
    }
}
        stage('Java API Tests') {
            steps {
                sh 'docker compose exec -T manager_app mvn test -Papi'
            }
        }

        stage('Java E2E Tests') {
            steps {
                sh 'docker compose exec -T manager_app mvn test -Pe2e'
            }
        }
    }

    post {
        always {
            sh 'docker compose down -v --remove-orphans'
        }
    }
}
