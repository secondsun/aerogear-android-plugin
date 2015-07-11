package net.jboss.aerogear.android.plugin.test

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Before

/**
 * Created by summers on 7/10/15.
 */
trait HasAndroidProject {

    Project project;

    @Before
    void createProject() {
        project = ProjectBuilder.builder().withProjectDir(
                new File("basic")).build()
        project.apply plugin: 'android'
        project.android {
            compileSdkVersion 15
            signingConfigs {
                fakeConfig {
                    storeFile project.file("aa")
                    storePassword "bb"
                    keyAlias "cc"
                    keyPassword "dd"
                }
            }
            defaultConfig {
                versionCode 1
                versionName "2.0"
                minSdkVersion 2
                targetSdkVersion 3
                signingConfig signingConfigs.fakeConfig
            }
        }
    }

}