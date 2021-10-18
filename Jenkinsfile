def regions = [:]
def environment = 'staging'
def map = ['staging':'us', 'prod':'eu']
def foo = ["1", "2", "3"]
def targetEnv = 'staging'

def stages() {
    def parallelStagesFromMap = foo.collectEntries {
        println('ParallelStage' + params.deploy_env)
        ["Build ${it}" : generateStage(params.deploy_env,it)]
    }
    return parallelStagesFromMap
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
        }
    }
}

node {
    stage('config') {
        println('config '+params.deploy_env)
        targetEnv = params.deploy_env
        foo = params.deploy_env
    }
    parallel stages

//     generateStage("skipped") // no invocation, stage is skipped
//
//     generateStage("nonparallel").call()
}