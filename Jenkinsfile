pipeline {
  agent any
  stages {
    stage('Gradle Build') {
        stages {
            stage('Build 01') {
                sh 'gradle clean build'
            }
            stage('Build 02') {
                sh 'gradle clean build'
            }
        }
    }
    stage('Deploy') {
        sh 'gradle clean build'
    }
  }
}
