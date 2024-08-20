# Look-data-hsqldb HOWTO

Here's some miscellaneous documentation about using and developing
Look Data Hsqldb.

# Release

Make sure that `mvn clean install site` runs on JDK 8, 11, 17 and 21
on Linux and Windows.
Also check [Travis CI](https://travis-ci.org/hydromatic/look-data-hsqldb).

Update the [release history](HISTORY.md),
the version number at the bottom of [README](README.md),
and the copyright date in [NOTICE](NOTICE).

Switch to JDK 21.

Set up tty so that gpg can prompt for password:
```
export GPG_TTY=$(tty)
```

Check that the sandbox is clean:
```
./mvnw clean
./mvnw release:clean
git clean -nx
git clean -fx
```

Prepare and perform:
```
./mvnw -Prelease -DreleaseVersion=x.y -DdevelopmentVersion=x.(y+1)-SNAPSHOT release:prepare
./mvnw -Prelease release:perform
```

Then go to [Sonatype](https://oss.sonatype.org/#stagingRepositories),
log in, close the repository, and release.

[Announce the release](https://twitter.com/julianhyde/status/622842100736856064).
