package com.Blood.Ware.miscs.discordhelper;
/*
 * Copyright 2016 - 2019 Florian Spieß and the java-discord-rpc contributors
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


import com.sun.jna.Library;
import com.sun.jna.Native;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Core library binding for the official <a href="https://github.com/discordapp/discord-rpc" target="_blank">Discord RPC SDK</a>.
 * <br>Use {@link #INSTANCE} to access this library.
 *
 * <h1>Supported Architectures</h1>
 * <ul>
 *   <li>Windows x86</li>
 *   <li>Windows x86-64</li>
 *   <li>Linux x86-64</li>
 *   <li>Darwin</li>
 * </ul>
 */
public interface DiscordRPC extends Library {
    /**
     * Library instance.
     */
    DiscordRPC INSTANCE = Native.loadLibrary("discord-rpc", DiscordRPC.class);

    /**
     * Used to decline a request via {@link #Discord_Respond(String, int)}
     *
     * @see #DISCORD_REPLY_YES
     */
    int DISCORD_REPLY_NO = 0;
    /**
     * Used to accept a request via {@link #Discord_Respond(String, int)}
     *
     * @see #DISCORD_REPLY_NO
     */
    int DISCORD_REPLY_YES = 1;
    /**
     * Currently unsused response, treated like NO.
     * Used with {@link #Discord_Respond(String, int)}
     *
     * @see #DISCORD_REPLY_NO
     */
    int DISCORD_REPLY_IGNORE = 2;


    void Discord_Initialize(@Nonnull String applicationId,
                            @Nullable DiscordEventHandlers handlers,
                            boolean autoRegister,
                            @Nullable String steamId);

    /**
     * Shuts the RPC connection down.
     * If not currently connected, this does nothing.
     */
    void Discord_Shutdown();

    /**
     * Executes the registered handlers for currently queued events.
     * <br>If this is not called the handlers will not receive any events!
     *
     * <p>It is recommended to call this in a <u>2 second interval</u>
     */
    void Discord_RunCallbacks();

    /**
     * Polls events from the RPC pipe and pushes the currently queued presence.
     * <br>This will be performed automatically if the attached binary
     * has an enabled IO thread (default)
     *
     * <p><b>If the IO-Thread has been enabled this will not be supported!</b>
     */
    void Discord_UpdateConnection();


    void Discord_UpdatePresence(@Nullable DiscordRichPresence struct);

    /**
     * Clears the currently set presence.
     */
    void Discord_ClearPresence();


    void Discord_Respond(@Nonnull String userid, int reply);

    /**
     * Updates the registered event handlers to the provided struct.
     *
     * @param handlers The handlers to update to, or null
     */
    void Discord_UpdateHandlers(@Nullable DiscordEventHandlers handlers);

    /**
     * Registers the given application so it can be run by the discord client. {@code discord-<appid>://}
     *
     * @param applicationId The ID of the application to register
     * @param command       The command for the application startup, or {@code null} to use the
     *                      current executable's path
     */
    void Discord_Register(String applicationId, String command);

    /**
     * Similar to {@link #Discord_Register(String, String)} but uses the steam
     * game's installation path.
     *
     * @param applicationId The ID of the application to register
     * @param steamId       The steam ID for the game
     */
    void Discord_RegisterSteamGame(String applicationId, String steamId);
}
