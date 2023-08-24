package info.nightscout.plugins.constraints.versionChecker

import dagger.android.HasAndroidInjector
import info.nightscout.interfaces.Config
import info.nightscout.interfaces.constraints.Constraint
import info.nightscout.interfaces.constraints.Constraints
import info.nightscout.interfaces.notifications.Notification
import info.nightscout.interfaces.plugin.PluginBase
import info.nightscout.interfaces.plugin.PluginDescription
import info.nightscout.interfaces.plugin.PluginType
import info.nightscout.interfaces.ui.UiInteraction
import info.nightscout.interfaces.versionChecker.VersionCheckerUtils
import info.nightscout.plugins.constraints.R
import info.nightscout.rx.bus.RxBus
import info.nightscout.rx.logging.AAPSLogger
import info.nightscout.shared.interfaces.ResourceHelper
import info.nightscout.shared.sharedPreferences.SP
import info.nightscout.shared.utils.DateUtil
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.roundToInt

@Singleton
class VersionCheckerPlugin @Inject constructor(
    injector: HasAndroidInjector,
    private val sp: SP,
    rh: ResourceHelper,
    private val versionCheckerUtils: VersionCheckerUtils,
    val rxBus: RxBus,
    aapsLogger: AAPSLogger,
    private val config: Config,
    private val dateUtil: DateUtil,
    private val uiInteraction: UiInteraction
) : PluginBase(
    PluginDescription()
        .mainType(PluginType.CONSTRAINTS)
        .neverVisible(true)
        .alwaysEnabled(true)
        .showInList(false)
        .pluginName(R.string.version_checker),
    aapsLogger, rh, injector
), Constraints {

    enum class GracePeriod(val warning: Long, val old: Long, val veryOld: Long) {
        RELEASE(30, 60, 90),
        RC(1, 7, 14)
    }

    companion object {

        private val WARN_EVERY: Long
            get() = TimeUnit.DAYS.toMillis(1)
    }

    override fun isClosedLoopAllowed(value: Constraint<Boolean>): Constraint<Boolean> {
        value.set(aapsLogger, true)
        return value
    }

    override fun applyMaxIOBConstraints(maxIob: Constraint<Double>): Constraint<Double> = maxIob

}
