pipeline {
  agent any
  parameters{
      choice( name: 'deploy_env',
              choices: 'dev\nstage\nprod',
              description: 'Choose deployment location')
      booleanParam(
                      name: 'User Approval Needed?',
                      defaultValue: true,
                      description: 'User approval required stages')
      string(
              name: 'String param',
              defaultValue: 'Default',
              description: 'description')
  }
  stages {
    stage('Init') {
      steps {
          sh 'echo init'
      }
    }
    stage('Infrastructure') {
        steps {
            script {
                doDynamicParallelSteps()
            }
        }
    }
  }
}

def doDynamicParallelSteps() {
    jobs = [:]
    def data = readProperties file: 'staging.properties'
    for (item in data.regions.split(',')) {
        jobs["${item}"] = {
            node {
                stage("${item}") {
                    echo "${item}"
                }
            }
        }
    }
    parallel jobs
}

