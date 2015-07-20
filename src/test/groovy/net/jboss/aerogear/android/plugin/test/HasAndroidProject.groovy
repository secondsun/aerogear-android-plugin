package net.jboss.aerogear.android.plugin.test

import com.android.build.gradle.AppPlugin
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Before

/**
 * Created by summers on 7/10/15.
 */
trait HasAndroidProject {

    Project project;
    Project upsProject;

    @Before
    void createBoringProject() {
        project = ProjectBuilder.builder().withProjectDir(
                File.createTempDir()).build()
        project.apply plugin: 'android'
        project.android {
            compileSdkVersion 21
            buildToolsVersion "22.0.1"
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

    @Before
    void createUPSProject() {

        upsProject = ProjectBuilder.builder().withProjectDir(
                File.createTempDir()).build()
        upsProject.apply plugin: 'android'
        upsProject.android {
            compileSdkVersion 21
            buildToolsVersion "22.0.1"
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
        new File((upsProject.plugins.withType(AppPlugin).extension.sourceSets[0][2]).assets.getSrcDirs()[0].absolutePath).mkdirs();
        def file = new File((upsProject.plugins.withType(AppPlugin).extension.sourceSets[0][2]).assets.getSrcDirs()[0].absolutePath + '/push-config.json');
        file.createNewFile()
        file << """{
          "pushServerURL": "https://localhost:8080/ag-push",
          "android": {
            "senderID": "123456",
            "variantID": "8abfae4eb02a6140c0a20798433180a063fd7006",
            "variantSecret": "5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8"
          }
        }"""
    }

}