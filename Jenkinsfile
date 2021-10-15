def regions = [:]
def environment = 'staging'
def map = ['staging':'us', 'prod':'eu']
def foo = ["1", "2", "3"]
def targetEnv = 'staging'

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
    }
}

node {
    stage('config') {
        println('config '+params.deploy_env)
        targetEnv = params.deploy_env
    }
    parallel parallelStagesFromMap

//     generateStage("skipped") // no invocation, stage is skipped
//
//     generateStage("nonparallel").call()
}