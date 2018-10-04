# ete-toolchain

A flexible MDE tool.


WARNING : this project depends on the util project :
  https://github.com/jean-luc-insset/util.git
The util project must be cloned and built in order to build the ete-toolchain project.


The tool has been architectured to be highly customizable.
It is aims to generate high quality code leveraging OCL constraints.


The project contains several sub projects :
  - ete
  - samples

ete is the actual MDE tool, composed of several sub-projects.
Main sub-projects are :
  - meta-model specifies the basic meta-model
  - meta-model-impl provides a default implementation of the meta-model
  - meta-model-io specifies reading and writing models
  - xmi-io implements meta-model-io for XMI files (currently, reading only)
  - ete-api specifies the process through the action interface
  - ete-api-impl defines some actions
  - ete-maven-plugin wraps the process into a maven plugin

Some other sub-projects are partly or entirely generated.

This is a new release which has been completely rewritten.
- the architecture itself uses abstract factories everywhere
- the factories are accessed through hierarchical registries
- the custom xslt-based ete language has been dropped in favor of Velocity


