package ci.orange.sicdomotique

import android.app.Application
//import android.util.Log
//import com.thingclips.smart.api.MicroContext
//import com.thingclips.smart.api.service.RedirectService
//import com.thingclips.smart.commonbiz.bizbundle.family.api.AbsBizBundleFamilyService
import com.thingclips.smart.home.sdk.ThingHomeSdk
//import com.thingclips.smart.optimus.sdk.ThingOptimusSdk
//import com.thingclips.smart.theme.ThingThemeInitializer.init
//import com.thingclips.smart.wrapper.api.ThingWrapper
//import com.thingclips.stencil.component.webview.config.GlobalConfig.context
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class BasApplication :Application() {
    override fun onCreate() {
        super.onCreate()
        ThingHomeSdk.init(this)
        ThingHomeSdk.setDebugMode(true)


        // Initializes the BizBundle.
        // Initializes the BizBundle.
//        ThingWrapper.init(
//            this,
//            { errorCode, urlBuilder -> // The callback of an unimplemented route.
//                // An unresponsive touch indicates an unimplemented route. To implement the route, set `urlBuilder.target` to the required route and set routing parameters in `urlBuilder.params`.
//                Log.e("router not implement", urlBuilder.target + urlBuilder.params.toString())
//            }) { serviceName -> // The callback of an unimplemented service.
//            Log.e("service not implement", serviceName)
//        }
//        init(context)
//        ThingOptimusSdk.init(this)
//
//        // Registers the home service. This service is not required for the Mall UI BizBundle.
//
//        // Registers the home service. This service is not required for the Mall UI BizBundle.
//        ThingWrapper.registerService<AbsBizBundleFamilyService, AbsBizBundleFamilyService>(
//            AbsBizBundleFamilyService::class.java, BizBundleFamilyServiceImpl()
//        )
//        // Intercepts an existing route and navigates to a custom page based on specific parameters.
//        // Intercepts an existing route and navigates to a custom page based on specific parameters.
//        val service = MicroContext.getServiceManager().findServiceByInterface<RedirectService>(
//            RedirectService::class.java.name
//        )
//        service.registerUrlInterceptor { urlBuilder, interceptorCallback -> //Such as:
//            // Intercept the event of tapping the top-right corner of the panel to navigate to a custom page specified by the parameters of `urlBuilder`.
//            if (urlBuilder.target == "panelAction" && urlBuilder.params.getString("action") == "gotoPanelMore") {
//                interceptorCallback.interceptor("interceptor")
//                Log.e("interceptor", urlBuilder.params.toString())
//            } else {
//                interceptorCallback.onContinue(urlBuilder)
//            }
//        }


    }
}