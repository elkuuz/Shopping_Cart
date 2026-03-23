Spipeline {
    agent any

    options {
        timestamps()
        disableConcurrentBuilds()
    }

    // Update these names to match Jenkins Global Tool Configuration.
    tools {
        jdk 'jdk21'
        maven 'maven3'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Test') {
            steps {
                sh 'mvn -B clean test'
            }
        }

        stage('Verify') {
            steps {
                sh 'mvn -B verify'
            }
        }
    }

    post {
        always {
            junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'
            archiveArtifacts allowEmptyArchive: true, artifacts: 'target/surefire-reports/**,target/jacoco.exec,target/site/jacoco/**'
        }
    }
}

