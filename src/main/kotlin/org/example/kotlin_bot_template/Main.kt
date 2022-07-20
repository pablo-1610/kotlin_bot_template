package org.example.kotlin_bot_template

import dev.minn.jda.ktx.jdabuilder.injectKTX
import io.github.cdimascio.dotenv.Dotenv
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.requests.GatewayIntent
import net.dv8tion.jda.api.utils.MemberCachePolicy
import net.dv8tion.jda.api.utils.cache.CacheFlag
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.system.exitProcess

lateinit var Env: Dotenv
lateinit var Bot: JDA
lateinit var Logger: Logger

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        Logger = LoggerFactory.getLogger(this::class.qualifiedName)
        Logger.info("Starting bot...")
        Env = Dotenv.load()

        if (Env["BOT_TOKEN"] == null) {
            Logger.error("Discord bot token is not set in .env file, aborting...")
            exitProcess(1)
        }

        val builder = JDABuilder.createDefault(Env["BOT_TOKEN"])
        builder.enableIntents(GatewayIntent.getIntents(GatewayIntent.ALL_INTENTS))
        builder.addEventListeners(EventHandler())
        builder.setMemberCachePolicy(MemberCachePolicy.ALL)
        builder.enableCache(
            CacheFlag.ACTIVITY, CacheFlag.CLIENT_STATUS, CacheFlag.EMOTE, CacheFlag.MEMBER_OVERRIDES,
            CacheFlag.ROLE_TAGS, CacheFlag.VOICE_STATE, CacheFlag.ONLINE_STATUS
        )
        builder.injectKTX()

        Bot = builder.build()
    }
}
