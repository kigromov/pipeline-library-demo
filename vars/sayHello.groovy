#!/usr/bin/env groovy
import hudson.tasks.test.AbstractTestResultAction
import hudson.tasks.junit.CaseResult
def call(String name = 'human') {
   stage("start test job") 
    {
       echo "START JOB"
       catchError {
        build job: 'AllureTest/allure', parameters: [string(name: 'testList', value: '*')]
       }
       echo "END JOB"
        def testResultAction = currentBuild.rawBuild.getAction(AbstractTestResultAction.class)
        def failedTests = testResultAction.getFailedTests()
        def failedTestsString = "```"
        for(CaseResult cr : failedTests) {
            failedTestsString = failedTestsString + "${cr.getFullDisplayName()}:\n${cr.getErrorDetails()}\n\n"
         }
        echo failedTestsString
    }
            
}

