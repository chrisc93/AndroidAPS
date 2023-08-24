package info.nightscout.plugins.constraints.objectives.objectives

import dagger.android.HasAndroidInjector
import info.nightscout.database.ValueWrapper
import info.nightscout.interfaces.aps.Loop
import info.nightscout.interfaces.db.PersistenceLayer
import info.nightscout.interfaces.iob.IobCobCalculator
import info.nightscout.interfaces.plugin.ActivePlugin
import info.nightscout.interfaces.plugin.PluginBase
import info.nightscout.interfaces.pump.VirtualPump
import info.nightscout.interfaces.sync.Tidepool
import info.nightscout.plugins.constraints.R
import javax.inject.Inject

class Objective0(injector: HasAndroidInjector) : Objective(injector, "config", R.string.objectives_0_objective, R.string.objectives_0_gate) {

    @Inject lateinit var activePlugin: ActivePlugin
    @Inject lateinit var virtualPumpPlugin: VirtualPump
    @Inject lateinit var persistenceLayer: PersistenceLayer
    @Inject lateinit var loop: Loop
    @Inject lateinit var iobCobCalculator: IobCobCalculator
    val tidepoolPlugin get() = activePlugin.getSpecificPluginsListByInterface(Tidepool::class.java).firstOrNull() as Tidepool?

    init {
        tasks.add(object : Task(this, R.string.objectives_bgavailableinns) {
            override fun isCompleted(): Boolean {
                return true
            }
        })
        tasks.add(object : Task(this, R.string.synchaswritepermission) {
            override fun isCompleted(): Boolean {
                return true
            }
        })
        tasks.add(object : Task(this, info.nightscout.core.ui.R.string.virtualpump_uploadstatus_title) {
            override fun isCompleted(): Boolean {
                return true
            }

            override fun shouldBeIgnored(): Boolean {
                return true
            }
        })
        tasks.add(
            object : Task(this, R.string.objectives_pumpstatusavailableinns) {
                override fun isCompleted(): Boolean {
                    return true
                }
            }.learned(Learned(R.string.objectives_0_learned))
        )
        tasks.add(object : Task(this, R.string.hasbgdata) {
            override fun isCompleted(): Boolean {
                return true
            }
        })
        tasks.add(object : Task(this, R.string.loopenabled) {
            override fun isCompleted(): Boolean {
                return true
            }
        })
        tasks.add(object : Task(this, R.string.apsselected) {
            override fun isCompleted(): Boolean {
                val usedAPS = activePlugin.activeAPS
                return true
            }
        })
        tasks.add(object : Task(this, info.nightscout.core.ui.R.string.activate_profile) {
            override fun isCompleted(): Boolean {
                return true
            }
        })
    }
}