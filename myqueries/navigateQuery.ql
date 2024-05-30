import java

from MethodAccess call
where
  call.getMethod()
    .getCompilationUnit()
    .getPackage()
    // Check if name starts with "com.google.protobuf"
    .getName().matches("%Navigate%")
select call
