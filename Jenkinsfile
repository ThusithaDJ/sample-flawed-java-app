def regions = [:]
def environment = 'staging'
def foo = ["1", "2", "3"]
def targetEnv = 'staging'
def map = [ 'staging':['us', 'eu'],
            'dev':['us', 'eu']]

@NonCPS
def handleParams() {
    foo = params.deploy_env
    println('HandlePrams')
}

def parallelStagesFromMap = foo.collectEntries {
    println('ParallelStage' + params.deploy_env)
    ["Build ${it}" : generateStage(params.deploy_env,it)]
}

def generateStage(env, bar) {
    return {
        stage("Build ${bar}") {
            echo "Building ${env} for ${bar}"
        }
        stage("test ${bar}") {
            echo "Building for ${bar}"
        }
        stage('deploy') {
            input (message: "Do you want to continue?")
            if (env.BRANCH_NAME == 'dev') {
                echo "Building for ${bar}"
            }
        }
    }
}

def doDynamicParallelSteps(foos) {
    println('doDynamicParallelSteps')
    println(foos)
    def vals = foos.get('dev')
  tests = [:]
  for (f in vals) {
    def val = f
    tests["${f}"] = {
      node {
        skipDefaultCheckout()

        def scmvars = checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/ThusithaDJ/sample-flawed-java-app.git']]])
//         def scmvars = checkout([
//                         $class: 'GitSCM',
//                         branches: [[name: '*/master']],
//                         doGenerateSubmoduleConfigurations: false,
//                         extensions: scm.extensions + [[$class: 'LocalBranch'], [$class: 'WipeWorkspace']],
//                         userRemoteConfigs: scm.userRemoteConfigs
//                                 ])

        def commitHash = scmvars.GIT_COMMIT
//         def gitBranch = scmvars.GIT_BRANCH ? scmvars.GIT_BRANCH : BRANCH
//
//         println("Branch :" + BRANCH)
        println("============================= SCM BRANCH :"+ scmvars.GIT_BRANCH)
        println("============================= env BRANCH :"+ env.BRANCH_NAME)

        stage("${val}") {
          echo '===================================${f}'
          sh '''
                cat README.md
          '''
        }
        stage("Build ${val}") {
            echo "==================================Building ${val} for ${f}"
        }
        stage("test ${val}") {
            echo "=============================Building for ${val}"
        }
        stage('deploy test ${val}') {
            if (env.BRANCH_NAME == 'master') {
                input (message: "Do you want to continue?")
                echo "Building for ${val}"
            }
        }
      }
    }
  }
  parallel tests
}

node {
            def fool = ['stage':["1", "2"],
                        'dev':["1", "2", "3"]]
    try {
        stage('config') {
            println('config '+params.deploy_env)
            handleParams()
            targetEnv = params.deploy_env
            foo = params.deploy_env
        }
        //     parallel parallelStagesFromMap

        //     generateStage("skipped") // no invocation, stage is skipped
//         println(params.deploy_env)
//         stage ('Infrastructure') {
//             generateStage("sdfghdfg", "nonparallel").call()
//         }
        stage ('Infrastructure') {
//             generateStage("sdfghdfg", "nonparallel").call()
            doDynamicParallelSteps(fool)
        }

    } catch(err) {
        println("ERR: ${err}")
        currentBuild.result = 'FAILED'
    }
}