package net.jboss.aerogear.android.plugin.task

import com.android.build.gradle.internal.dependency.ManifestDependencyImpl
import com.android.build.gradle.tasks.ProcessManifest
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.Nested
import org.gradle.api.tasks.TaskAction

class AddUPSToManifestTask extends ProcessManifest {


    protected void doFullTaskAction() {
//        getBuilder().processManifest(
//                getMainManifest(),
//                getManifestOverlays(),
//                getLibraries(),
//                getPackageNameOverride(),
//                getVersionCode(),
//                getVersionName(),
//                getMinSdkVersion(),
//                getTargetSdkVersion(),
//                getManifestOutputFile().absolutePath)
    }
}
