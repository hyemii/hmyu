import groovy.json.JsonOutput

node {

    def scmVars = checkout scm
    def values = scmVars.GIT_BRANCH.tokenize('/')
    String branchName = values[1]

    try {
        if (branchName == 'master') {
            gitCheckout(branchName)
            GradleBuild()
            NpmBuild()
            unitTest()
            //notifySlack('SUCCESS')
        } else {
            gitCheckout(branchName)
            GradleBuild()
            NpmBuild()
            unitTest()
            //notifySlack('SUCCESS')
        }
    } catch (env) {
        //notifySlack('FAIL')
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

def NpmBuild() {
    stage('Npm Build') {
        dir('frontend') {
            sh "npm run build"
        }
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

/*def notifySlack(String buildStatus) {

    def color

    if (buildStatus == 'SUCCESS') {
        color = '#1a9367'
    } else if (buildStatus == 'UNSTABLE') {
        color = '#FFFE89'
    } else {
        color = '#ff0000'
    }

    def msg = "${buildStatus}: `${env.JOB_NAME}` #${env.BUILD_NUMBER}:\n${env.BUILD_URL}"
    slackSend(channel: "${SLACK_CHANNEL}", color: color, message: msg)
}*/
