# ✦ Item Glow Mod — Fabric 1.21.4

Мод добавляет возможность подсвечивать все выброшенные предметы (дропы) на земле **сквозь стены** по нажатию клавиши.

---

## 🎮 Как пользоваться

| Действие | Клавиша |
|---|---|
| Включить/выключить свечение | **G** (по умолчанию) |

Клавишу можно переназначить в **Настройки → Управление → Item Glow**.

При включении в action bar появится сообщение:
- `✦ Item Glow: ВКЛЮЧЕН` — предметы светятся сквозь стены
- `✦ Item Glow: ВЫКЛЮЧЕН` — всё как обычно

---

## 🔧 Сборка

### Требования
- Java 21+
- [Gradle](https://gradle.org/) (или используй `./gradlew`)

### Шаги сборки

```bash
# 1. Перейди в папку мода
cd item-glow-mod

# 2. Собери мод
./gradlew build

# 3. Готовый .jar будет здесь:
# build/libs/item-glow-1.0.0.jar
```

На Windows используй `gradlew.bat build`.

---

## 📦 Установка

1. Установи [Fabric Loader](https://fabricmc.net/use/) для Minecraft 1.21.4
2. Скачай [Fabric API](https://modrinth.com/mod/fabric-api) для 1.21.4
3. Положи `item-glow-1.0.0.jar` и `fabric-api-*.jar` в папку `.minecraft/mods/`
4. Запусти игру!

---

## ⚙️ Как это работает

Мод использует **Mixin** для перехвата метода `isGlowing()` у класса `ItemEntity`.
Когда режим включён — метод всегда возвращает `true`, что заставляет Minecraft
рендерить светящийся контур вокруг каждого предмета, **включая видимость сквозь стены**.

Это клиентский мод — **не требует установки на сервер**.

---

## 📁 Структура проектаа

```
src/
├── main/java/com/itemglow/mixin/
│   └── ItemEntityMixin.java       ← Mixin: перехват isGlowing()
├── main/resources/
│   ├── fabric.mod.json
│   ├── itemglow.mixins.json
│   └── assets/itemglow/lang/
│       ├── ru_ru.json
│       └── en_us.json
└── client/java/com/itemglow/
    └── ItemGlowClient.java        ← Клавиша, логика включения
```
