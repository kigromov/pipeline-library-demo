#!/usr/bin/env groovy

def call(String name = 'human') {
   stage("start test job") 
    {
        build job: 'AllureTest/allure', parameters: [string(name: 'testList', value: '*')]
    }
               stage("get failed test") {
                def testResultAction = currentBuild.rawBuild.getAction(AbstractTestResultAction.class)
                def failedTests = testResultAction.getFailedTests()
                def failedTestsString = "```"
                for(CaseResult cr : failedTests) {
                    failedTestsString = failedTestsString + "${cr.getFullDisplayName()}:\n${cr.getErrorDetails()}\n\n"
                }
                echo failedTestsString
            }
}

