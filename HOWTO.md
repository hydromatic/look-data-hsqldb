# Chinook-data-hsqldb HOWTO

Here's some miscellaneous documentation about using and developing
Chinook Data Hsqldb.

# Release

Make sure that `mvn clean install site` runs on JDK 7, 8, 9, 10,
on Linux and Windows.
Also check [Travis CI](https://travis-ci.org/julianhyde/chinook-data-hsqldb).

Update the [release history](HISTORY.md),
the version number at the bottom of [README](README.md),
and the copyright date in [NOTICE](NOTICE).

Using JDK 8, do the following steps:

```
mvn clean
mvn release:clean
git clean -nx
git clean -fx
read -s GPG_PASSPHRASE
mvn -Prelease -Dgpg.passphrase=${GPG_PASSPHRASE} release:prepare
mvn -Prelease -Dgpg.passphrase=${GPG_PASSPHRASE} release:perform
```

Then go to [Sonatype](https://oss.sonatype.org/#stagingRepositories),
log in, close the repository, and release.

Make sure that the [site](http://www.hydromatic.net/chinook-data-hsqldb/) has been updated.

[Announce the release](https://twitter.com/julianhyde/status/622842100736856064).
