LogBlock 2
==========

A rewrite of the most successful block logging software.
--------------------------------------------------------

LogBlock 2 is a ground up rewrite of LogBlock designed to be more efficient and versatile. It is designed to learn from the mistakes of all other block logging plugins for Minecraft, and provide complete protection for the discerning server owner. LogBlock 2 will be heavily abstracted and optimized to allow efficient usage across many different Minecraft server platforms and database backends. The most stable and officially supported backend will be Bukkit in conjunction with MySQL, however we hope to allow the use of Forge as a server, as well as H2 as an integrated database.

Features
--------

* Connection pooling with BoneCP
* Ultra fast rollback
* Bukkit support
* Forge support
* MySQL support
* H2 support

Supported Log Entries
---------------------

* block-place
* block-break
* block-fall (sand, gravel)
* block-piston
* block-burn
* block-ignite
* explosion (TNT, Creeper)
* entity-change (Enderman, Ender Dragon)
* sign-text
* player-bucket
* leaf-decay
* structure-growth
* liquid-flow
* player-interact
* container-access
* worldedit

Database Schema
---------------
```SQL
CREATE TABLE IF NOT EXISTS lb_meta (
 db_version tinyint unsigned NOT NULL
);
```

```SQL
CREATE TABLE IF NOT EXISTS lb_player (
 id mediumint unsigned NOT NULL,
 username varchar(16) NOT NULL,`
 PRIMARY KEY (id),
 UNIQUE KEY username (username)
);
```

```SQL
CREATE TABLE IF NOT EXISTS lb_world_blob (
 id int unsigned NOT NULL,
 type tinyint unsigned NOT NULL,
 data blob NOT NULL
 PRIMARY KEY (id)
);
```

```SQL
CREATE TABLE IF NOT EXISTS lb_world_text (
 id int unsigned NOT NULL,
 text varchar(255) NOT NULL,
 PRIMARY KEY (id)
);
```

```SQL
CREATE TABLE IF NOT EXISTS lb_world_item (
 id int unsigned NOT NULL,
 type smallint unsigned NOT NULL,
 amount smallint NOT NULL,
 damage smallint unsigned NOT NULL,
 slot tinyint NOT NULL,
 nbt blob,
 PRIMARY KEY (id)
);
```

```SQL
CREATE TABLE IF NOT EXISTS lb_world (
 id int unsigned NOT NULL AUTO_INCREMENT,
 action tinyint unsigned NOT NULL,
 date datetime NOT NULL,
 player mediumint unsigned NOT NULL,
 from smallint unsigned NOT NULL,
 fromdata tinyint unsigned NOT NULL,
 to smallint unsigned NOT NULL,
 todata tinyint unsigned NOT NULL,
 x mediumint NOT NULL,
 y smallint NOT NULL,
 z mediumint NOT NULL,
 PRIMARY KEY (id),
 KEY position (x,z,y),
 KEY date (date),
 KEY player (player)
);
```
