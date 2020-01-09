node {
    def scmVars = checkout scm
    def values = scmVars.GIT_BRANCH.tokenize('/')
    String branchName = values[1]

    try {
        gitCheckout(branchName)
        GradleBuild()
        NpmBuild()
        unitTest()
    } catch (env) {
        throw env
    }
}

def gitCheckout(String branch) {
    stage('Git Checkout') {
        echo 'Checkout ' + branch
        git branch: branch, credentialsId: "${CREDENTIALIS_ID}", url: "${GIT_URL}"
    }
}

def GradleBuild() {
    stage('Gradle Build') {
        sh "./gradlew clean build"
    }
}

def unitTest() {
    stage('Unit tests') {
        sh "./gradlew test"
        if (currentBuild.result == "UNSTABLE") {
            sh "exit 1"
        }
    }
}