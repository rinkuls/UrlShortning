import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.freeDiskSpace
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.VcsTrigger
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs
/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

project {


    sequential {
        buildType(Build)
        buildType(Package)
    }
}

object Build : BuildType({
    name = "Build"

    vcs {
        root(DslContext.settingsRoot)
    }
    features{
        freeDiskSpace {
            requiredSpace = "4gb"
            failBuild = true
        }

    }
    steps {
        maven {
            name = "RinkulStep"
            goals = "clean compile"
            runnerArgs = "-Dmaven.test.failure.ignore=true"
            mavenVersion = bundled_3_5()
        }
    }

})


object Package : BuildType({
    name = "package"

    vcs {
        root(DslContext.settingsRoot)
    }
    features{
        freeDiskSpace {
            requiredSpace = "4gb"
            failBuild = true
        }

    }
    steps {
        maven {
            name = "RinkulStep"
            goals = "clean package"
            runnerArgs = "-Dmaven.test.failure.ignore=true -DskipTests"
            mavenVersion = bundled_3_5()
        }
    }


    //dependencies {
    // snapshot(Build){

    //  }
    //}

    triggers {
        vcs {
            quietPeriodMode = VcsTrigger.QuietPeriodMode.USE_CUSTOM
            quietPeriod = 5
            perCheckinTriggering = true
            groupCheckinsByCommitter = true
            enableQueueOptimization = false
        }

    }
})