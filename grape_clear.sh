#!/bin/bash

version=$1
grape uninstall org.reksio.rfp tools $version
grape uninstall org.reksio.rfp common $version
grape uninstall org.reksio.rfp fakt $version
grape uninstall org.reksio.rfp rest $version
grape uninstall org.reksio.rfp smallbusiness $version
