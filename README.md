# 🕵️ Minecraft Hide and Seek Plugin

![GitHub Release (latest by date)](https://img.shields.io/github/v/release/Mikolaj0524/HideAndSeek?label=latest%20release)
![GitHub License](https://img.shields.io/github/license/Mikolaj0524/HideAndSeek)
![PaperMC](https://img.shields.io/badge/Server-Paper%201.20+-blue)
![Java](https://img.shields.io/badge/Java-17+-orange)
![PlaceholderAPI](https://img.shields.io/badge/PlaceholderAPI-Supported-success)

A fun and configurable **Hide and Seek** plugin for Paper/Spigot servers.
Players can disguise as blocks and hide while one or more hunters try to find them before the timer runs out.

---

## ✨ Features

* 🧱 **Hide as blocks** – players disguise as blocks defined per map.
* 🗺️ **Multiple maps** – each with its own spawn location and block list.
* 🎯 **Hunter selection** – choose manually or let the plugin pick randomly.
* 🧰 **Full GUI management** – easy setup using `/menu`.
* 🧩 **PlaceholderAPI support** – display map name, game time, and block data.
* ⚙️ **Simple YAML configuration** – quick setup and editing.

---

## ⚙️ Commands

| Command | Description                            | Permission |
| ------- | -------------------------------------- | ---------- |
| `/menu` | Opens the Hide and Seek management GUI | `hns.menu` |

---

## 🧩 PlaceholderAPI Placeholders

| Placeholder       | Description                                     |
| ----------------- | ----------------------------------------------- |
| `%hns_map_name%`  | Current selected map name                       |
| `%hns_game_time%` | Remaining game time                             |
| `%hns_block_X%`   | Block name available for disguises by index (X) |

---

## 🕹️ Gameplay Overview

1. Players join a Hide and Seek game on the selected map.
2. One player becomes the **Hunter** (either manually chosen or random).
3. Other players disguise as blocks and hide within the map.
4. The Hunter must find all hidden players before time runs out.

If time ends and any Hider remains hidden, **the Hiders win**.
If all are found, **the Hunter wins**.

---

## 🗺️ Configuration Example

```yaml
prefix: " \n&8 [&e&lHS&8] &6» &7"

spawn:
  x: 0
  y: 100
  z: 0
  yaw: 180

maps:
  city:
    loc:
      x: 100
      y: 100
      z: 100
      yaw: 90
    blocks:
      - STONE_BRICK_STAIRS
      - EMERALD_ORE
      - DIAMOND_ORE
      - COBBLESTONE_SLAB
```

### Explanation:

* **prefix** – the plugin’s chat prefix (supports color codes).
* **spawn** – main lobby spawn position.
* **maps** – defines available maps:

  * Each map has a name (e.g., `city`).
  * `loc` is the player spawn position for that map.
  * `blocks` lists which blocks hiders can disguise as.

---

## ⚡ Requirements

* Minecraft **Paper** or **Spigot** server (1.20+ recommended)
* **Java 17+**
* **PlaceholderAPI** for placeholders

---

## 💾 Installation

1. Download the latest `.jar` from the [Releases](../../releases) page.
2. Place it inside your server’s `/plugins/` folder.
3. Restart the server.
4. Configure maps and settings in `config.yml`.
5. Run `/menu` in-game to manage everything easily.

---

## 🧰 Building from Source

```bash
git clone https://github.com/Mikolaj0524/HideAndSeek.git
cd HideAndSeek
mvn clean package
```

The compiled `.jar` file will be in the `target/` folder.

---

## 🧑‍💻 Contributing

Contributions and pull requests are welcome!
If you have ideas for improvements or find bugs, open an issue on GitHub.

---

## 📜 License

This project is licensed under the **GPLv3 License**.
See the [LICENSE](LICENSE) file for details.

---
README.md by ChatGPT.