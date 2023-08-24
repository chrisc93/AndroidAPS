package info.nightscout.plugins.constraints.objectives.objectives

import dagger.android.HasAndroidInjector
import info.nightscout.interfaces.actions.Actions
import info.nightscout.interfaces.plugin.ActivePlugin
import info.nightscout.interfaces.plugin.PluginBase
import info.nightscout.plugins.constraints.R
import javax.inject.Inject

class Objective1 @Inject constructor(injector: HasAndroidInjector) : Objective(injector, "usage", R.string.objectives_usage_objective, R.string.objectives_usage_gate) {

    @Inject lateinit var activePlugin: ActivePlugin
    val actionsPlugin: PluginBase
        get() = activePlugin.getSpecificPluginsListByInterface(Actions::class.java)[0]

    init {
        tasks.add(object : Task(this, R.string.objectives_useprofileswitch) {
            override fun isCompleted(): Boolean {
                return true
            }
        })
        tasks.add(object : Task(this, R.string.objectives_usedisconnectpump) {
            override fun isCompleted(): Boolean {
                return true
            }
        }.hint(Hint(R.string.disconnectpump_hint)))
        tasks.add(object : Task(this, R.string.objectives_usereconnectpump) {
            override fun isCompleted(): Boolean {
                return true
            }
        }.hint(Hint(R.string.disconnectpump_hint)))
        tasks.add(object : Task(this, R.string.objectives_usetemptarget) {
            override fun isCompleted(): Boolean {
                return true
            }
        }.hint(Hint(R.string.usetemptarget_hint)))
        tasks.add(object : Task(this, R.string.objectives_useactions) {
            override fun isCompleted(): Boolean {
                return true
            }
        }.hint(Hint(R.string.useaction_hint)))
        tasks.add(object : Task(this, R.string.objectives_useloop) {
            override fun isCompleted(): Boolean {
                return true
            }
        }.hint(Hint(R.string.useaction_hint)))
        tasks.add(
            object : Task(this, R.string.objectives_usescale) {
                override fun isCompleted(): Boolean {
                    return true
                }
            }.hint(Hint(R.string.usescale_hint))
                .learned(Learned(R.string.objectives_usage_learned))
        )
    }
}