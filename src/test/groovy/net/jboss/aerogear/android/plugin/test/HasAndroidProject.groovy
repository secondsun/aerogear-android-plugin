/*
 * Copyright 2015 JBoss, Home of Professional Open Source
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
    Project brokenUpsProject;

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


            sourceSets {
                main {
                    manifest.srcFile  'src/test/resources/AndroidManifest-complete.xml'
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

        new File([upsProject.projectDir.absolutePath, "src", "test", "resources"].join(File.separator)).mkdirs();
        def manifestFile = new File([upsProject.projectDir.absolutePath, "src", "test", "resources", "AndroidManifest-complete.xml"].join(File.separator));
        manifestFile.createNewFile()
        manifestFile << new File('src/test/resources/AndroidManifest-complete.xml').text

    }

    @Before
    void createBrokenUPSProject() {

        brokenUpsProject = ProjectBuilder.builder().withProjectDir(
                File.createTempDir()).build()
        brokenUpsProject.apply plugin: 'android'
        brokenUpsProject.android {
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


            sourceSets {
                main {
                    manifest.srcFile  'src/test/resources/AndroidManifest-missing-everything.xml'
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
        new File((brokenUpsProject.plugins.withType(AppPlugin).extension.sourceSets[0][2]).assets.getSrcDirs()[0].absolutePath).mkdirs();
        def file = new File((brokenUpsProject.plugins.withType(AppPlugin).extension.sourceSets[0][2]).assets.getSrcDirs()[0].absolutePath + '/push-config.json');
        file.createNewFile()
        file << """{
          "pushServerURL": "https://localhost:8080/ag-push",
          "android": {
            "senderID": "123456",
            "variantID": "8abfae4eb02a6140c0a20798433180a063fd7006",
            "variantSecret": "5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8"
          }
        }"""

        new File([brokenUpsProject.projectDir.absolutePath, "src", "test", "resources"].join(File.separator)).mkdirs();
        def manifestFile = new File([brokenUpsProject.projectDir.absolutePath, "src", "test", "resources", "AndroidManifest-missing-everything.xml"].join(File.separator));
        manifestFile.createNewFile()
        manifestFile << new File('src/test/resources/AndroidManifest-missing-everything.xml').text

    }

}