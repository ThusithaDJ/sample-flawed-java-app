def regions = [:]

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
    stage('configs') {
        steps {
            script {
                def prop = readProperties file: 'staging.properties'
                regions = prop.regions.split(',')
                println(regions[0])
            }
        }
    }
    stage('Infrastructure') {
        parallel {
            stage('US Staging') {
                stages {
                    stage('Validate') {
                        steps {
                            sh 'echo us staging validate'
                        }
                    }
                    stage('Plan') {
                        steps {
                            sh 'echo us plan'
                        }
                    }
                    stage('Deploy') {
                        steps {
                            sh "echo Deploying to ${params.deploy_env}"
                        }
                    }
                }
            }
            stage('US Playground') {
                stages {
                    stage('Validate') {
                        steps {
                            sh 'echo play validate'
                        }
                    }
                    stage('Plan') {
                        steps {
                            sh 'echo play plan'
                        }
                    }
                    stage('Deploy') {
                        steps {
                            sh 'echo play destroy'
                        }
                    }
                }
            }
        }
    }
  }
}

