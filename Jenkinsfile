pipeline {
  agent any
  stages {
    stage('Gradle Build') {
        parallel {
            stage('Build 01') {
                steps {
                    sh 'gradle clean build'
                }
            }
            stage('Build 02') {
                steps {
                    sh 'gradle clean build'
                }
            }
        }
    }
    stage('Deploy') {
        steps {
            sh 'gradle clean build'
        }
    }
  }
}
