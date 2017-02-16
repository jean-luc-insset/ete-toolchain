# ete-toolchain

A flexible MDA tool.


WARNING : this project depends on the util project :
  https://github.com/jean-luc-insset/util.git
The util project must be cloned and built in order to build the ete-toolchain project.



The tool is highly customizable. it is intented to use OCL constraints to generate better quality code.

The project contains two sub projects : ete and samples.

ete is the actual MDA tool, composed of several sub-projects.
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
- the custom xslt-based ete language has been dropped, Velocity is used instead.

Currently, only one example is provides. It is a JSF example and the provided configuration files are suitable to run the project against Glassfish and its JavaDB database "sample".
The database can be set in the file
  samples/insset_airlines/src/main/mda/modules/jpa/pu.vm
It is supposed to be a velocity template but actually, it is an usual JPA persistence.xml file


