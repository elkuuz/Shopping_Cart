pipeline {
    agent any

    environment {
        DOCKERHUB_REPO = 'elkuuz/shopping-cart'
        IMAGE_TAG = "${BUILD_NUMBER}"
        // Jenkins non-login shells often miss Docker Desktop paths on macOS.
        PATH = "${env.PATH}:/usr/local/bin:/opt/homebrew/bin:/Applications/Docker.app/Contents/Resources/bin"
    }

    options {
        timestamps()
        disableConcurrentBuilds()
        skipDefaultCheckout(true)
    }

    // Update these names to match Jenkins Global Tool Configuration.
    tools {
        jdk 'JDK21'
        maven 'Maven'
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

        stage('Docker Preflight') {
            steps {
                sh 'echo "PATH=$PATH"'
                sh 'command -v docker'
                sh 'docker version'
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t ${DOCKERHUB_REPO}:${IMAGE_TAG} -t ${DOCKERHUB_REPO}:latest .'
            }
        }

        stage('Docker Push') {
            steps {
                script {
                    if (env.BRANCH_NAME == 'main' || env.BRANCH_NAME == 'master') {
                        withCredentials([usernamePassword(credentialsId: 'dockerhub-creds', usernameVariable: 'DOCKERHUB_USER', passwordVariable: 'DOCKERHUB_TOKEN')]) {
                            sh 'echo "$DOCKERHUB_TOKEN" | docker login -u "$DOCKERHUB_USER" --password-stdin'
                            sh 'docker push ${DOCKERHUB_REPO}:${IMAGE_TAG}'
                            sh 'docker push ${DOCKERHUB_REPO}:latest'
                            sh 'docker logout'
                        }
                    } else {
                        echo "Skipping Docker push for branch '${env.BRANCH_NAME}'. Push runs only on main/master."
                    }
                }
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

