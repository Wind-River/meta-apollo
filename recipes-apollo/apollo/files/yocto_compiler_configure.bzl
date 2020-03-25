# Copyright (c) 2019-2020 Wind River Systems, Inc. 
# Licensed under the Apache License, Version 2.0 (the "License"); 
# you may not use this file except in compliance with the License. 
# You may obtain a copy of the License at: 
#       http://www.apache.org/licenses/LICENSE-2.0 
# Unless required by applicable law or agreed to in writing, software  distributed 
# under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES 
# OR CONDITIONS OF ANY KIND, either express or implied. 

# -*- Python -*-
"""Yocto rule for yocto compiler autoconfiguration."""

def _tpl(repository_ctx, tpl, substitutions={}, out=None):
  if not out:
    out = tpl
  repository_ctx.template(
      out,
      Label("//third_party/toolchains/yocto:%s.tpl" % tpl),
      substitutions)


def _yocto_compiler_configure_impl(repository_ctx):
  _tpl(repository_ctx, "CROSSTOOL")
  repository_ctx.symlink(repository_ctx.attr.build_file, "BUILD")


yocto_compiler_configure = repository_rule(
    implementation = _yocto_compiler_configure_impl,
    attrs = {
        "remote_config_repo": attr.string(mandatory = False, default =""),
        "build_file": attr.label(),
    },
)
