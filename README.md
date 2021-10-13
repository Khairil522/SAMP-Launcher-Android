# SAMP-Launcher-Android
Ini adalah Launcher SAMP untuk android, ini hanya untuk mengganti nama dan kemungkinan di update lanjutnya akan mendukung download client. Bersenang senanglah!!!

# Build Test On
- Android Studio.
- AIDE(mobile).

# settings source code SAMP
- settings.cpp
```c++
#include "main.h"
#include "settings.h"
#include "vendor/inih/cpp/INIReader.h"

CSettings::CSettings()
{
	Log("Loading settings..");	

	char buff[0x7F];
	sprintf(buff, "%sSAMP/settings.ini", g_pszStorage);

	INIReader reader(buff);

	if(reader.ParseError() < 0)
	{
		Log("Error: can't load %s", buff);
		std::terminate();
		return;
	}
	
	LoadName();

	// client
	size_t length = 0;
	//sprintf(buff, "__android_%d%d", rand() % 1000, rand() % 1000);
	//length = reader.Get("client", "name", buff).copy(m_Settings.szNickName, MAX_PLAYER_NAME);
	m_Settings.szNickName[length] = '\0';
	length = reader.Get("client", "host", "127.0.0.1").copy(m_Settings.szHost, MAX_SETTINGS_STRING);
	m_Settings.szHost[length] = '\0';
	length = reader.Get("client", "password", "").copy(m_Settings.szPassword, MAX_SETTINGS_STRING);
	m_Settings.szPassword[length] = '\0';
	m_Settings.iPort = reader.GetInteger("client", "port", 7777);
	length = reader.Get("client", "authKey", "").copy(m_Settings.szAuthKey, MAX_SETTINGS_STRING);
	m_Settings.szAuthKey[length] = '\0';

	// debug
	m_Settings.bDebug = reader.GetBoolean("debug", "debug", false);
	m_Settings.bOnline = reader.GetBoolean("debug", "online", true);

	// gui
	strcpy(m_Settings.szFont, "Arial_2.ttf");
	m_Settings.fFontSize = 26.0f;
	m_Settings.iFontOutline = 2;
	
	// chat
	m_Settings.fChatPosX = 325.0f;
	m_Settings.fChatPosY = -15.0f;
	m_Settings.fChatSizeX = 1150.0f;
	m_Settings.fChatSizeY = 325.0f;
	m_Settings.iChatMaxMessages = 12;
	// spawnscreen
	m_Settings.fSpawnScreenPosX = 660.0f;
	m_Settings.fSpawnScreenPosY = 950.0f;
	m_Settings.fSpawnScreenSizeX = 600.0f;
	m_Settings.fSpawnScreenSizeY = 100.0f;
	// nametags
	m_Settings.fHealthBarWidth = 60.0f;
	m_Settings.fHealthBarHeight = 10.0f;
	// scoreboard
	m_Settings.fScoreBoardSizeX = 1024.0f;
	m_Settings.fScoreBoardSizeY = 768.0f;

	// passenger
	m_Settings.bPassengerUseTexture = reader.GetBoolean("gui", "PassengerUseTexture", true);
	m_Settings.fPassengerTextureSize = reader.GetReal("gui", "PassengerTextureSize", 20.0f);
	m_Settings.fPassengerTextureX = reader.GetReal("gui", "PassengerTexturePosX", 120.0f);
	m_Settings.fPassengerTextureY = reader.GetReal("gui", "PassengerTexturePosY", 430.0f);
	Log("Settings loaded.");
}

void LoadName()
{
	char c;
	char path[0x7F];
	sprintf(path, "/storage/emulated/0/Android/media/com.rockstargames.gtasa/settings.name");
	
	std::string buf;
	FILE *fp = fopen(path, "r");
	while (fscanf(fp, "%c", &c) != EOF)
	{
		buf += c;
    }
	fclose(fp);
	fp = NULL;

	// Client
	if(buf == " ") sprintf(buf, "__android_%d%d", rand() % 1000, rand() % 1000);
	cp1251_to_utf8(m_Settings.szNickName, buf.data());
	Log("Name loaded.");
}```
