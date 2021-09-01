pipeline {
  agent any
  stages {
    stage('build') {
      steps {
        gradlew('clean', 'classes')
      }
    }

  }
}
