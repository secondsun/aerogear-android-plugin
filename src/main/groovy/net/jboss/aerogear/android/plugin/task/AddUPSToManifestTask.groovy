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
package net.jboss.aerogear.android.plugin.task

import com.android.build.gradle.AppPlugin
import com.android.build.gradle.api.AndroidSourceFile
import com.android.build.gradle.internal.dependency.ManifestDependencyImpl
import com.android.build.gradle.tasks.ProcessManifest
import groovy.util.slurpersupport.NodeChild
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.Nested
import org.gradle.api.tasks.TaskAction
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import groovy.util.slurpersupport.Node

import java.util.concurrent.atomic.AtomicBoolean

class AddUPSToManifestTask extends DefaultTask {

    private static final String TAG = "UPSManifestCheck"
    
    private static final Logger LOG = LoggerFactory.getLogger('some-logger')


    @Input
    AndroidSourceFile manifest

    @TaskAction
    protected void run() {

        def manifestXml = new XmlSlurper().parse(manifest.srcFile);

        if(!checkPermissions(manifestXml)) {
            addPermissions();
        }

        if (!checkReceiver(manifestXml)) {
            addReceiver();
        }

        if (!checkService(manifestXml)) {
            addService();
        }

    }

    /**
     * Add the Broadcast receiver to the Android manifest for UPS to run.
     */
    def addReceiver() {

    }

    /**
     * Check that the manifest includes the correct broadcastReceiver to run UPS
     */
    def checkReceiver(manifest) {
        def receivers = manifest.application.children().findAll { node ->
            node.name() == 'receiver' && node['@android:name'] == 'org.jboss.aerogear.android.unifiedpush.gcm.AeroGearGCMMessageReceiver' && node['@android:permission'] == 'com.google.android.c2dm.permission.SEND'
        }
        if (receivers == null || receivers.size() != 1) {
            LOG.warn("A UPS Compatible BroadcastReceiver was not found")
            return false;
        }

        NodeChild receiver = receivers[0];

        def intentFilters = receiver.children().findAll { node ->
            AtomicBoolean foundIntentFilter = new AtomicBoolean(false);
            if (node.name() == 'intent-filter' ) {
                
                AtomicBoolean actionFound = new AtomicBoolean(false);
                AtomicBoolean categoryFound = new AtomicBoolean(false);
                    
                node.children().each { intentFilterChild ->
                    
                    if (intentFilterChild.name() == 'action' && !(actionFound.get())) {
                        actionFound.set(intentFilterChild['@android:name'] == "com.google.android.c2dm.intent.RECEIVE");
                    }
                    
                    if (intentFilterChild.name() == 'category' && !categoryFound.get()) {
                        categoryFound.set(intentFilterChild['@android:name'] == manifest['@package']);
                    }
                    
                    if (actionFound.get() && categoryFound.get()) {
                        foundIntentFilter.set(true);
                    }
                }
            }
            
            return foundIntentFilter.get();
        }

        if (intentFilters == null || intentFilters.size() != 1) {
            LOG.warn("A BroadcastReceiver was found, but the intent-filter was not properly configured")
            return false;
        }


        return true;
    }

    /**
     * Add the IDlistenerservice to the manifest
     */
    def addService() {

    }

    /**
     * Check that the manifest includes IDListenerService
     */
    def checkService(manifest) {
        return false;
    }

    /**
     * Add the necessary permissions to the Android Manifest for UPS to run.
     */
    def addPermissions() {

    }

    /**
     * Check that the manifest includes the correct permissions for using UPS.
     */
    def checkPermissions(manifest) {
        return false;
    }
}
