<%@  page pageEncoding="UTF-8" contentType="text/html;UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <!--引入BootStrap的css样式-->
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
    <!--BootStrap与JQGRID整合后的css样式-->
    <link rel="stylesheet" href="bootstrap-jqgrid/css/trirand/ui.jqgrid-bootstrap.css">
    <!--引入jquery的js文件-->
    <script src="bootstrap/js/jquery-3.3.1.min.js"></script>
    <!--引入BootStrap的js文件-->
    <script src="bootstrap/js/bootstrap.js"></script>
    <!--jqgrid与bootstrap整合的国际化的js文件-->
    <script src="bootstrap-jqgrid/js/trirand/i18n/grid.locale-cn.js"></script>
    <!--jqgrid与bootstrap整合的js文件-->
    <script src="bootstrap-jqgrid/js/trirand/jquery.jqGrid.min.js"></script>
    <style>
    </style>
    <script>
        $(function () {
           //通过jqGrid为表格渲染数据
            //page：定义初始化时的页号
            //rowNum: 定义每页展示的条数
           $("#table").jqGrid({
              styleUI:"Bootstrap",
              url:"${pageContext.request.contextPath}/user/showAll",//获取数据的地址
              datatype:"json",//自动把后台响应回的json协议串自动转换为jquery的对象或集合
              pager:"#pager", //开启分页的工具栏
              rowNum:3,//决定每页展示的条数
              rowList:["3","6","9","12"], //动态调整分页的条数
              viewrecords:true, //显示总条数
              caption:"员工列表",//定义表格名称
              editurl:"${pageContext.request.contextPath}/user/edit",
              colNames:["id","用户名","密码","手机号","邮箱","IP","身份","状态","操作"],
              colModel:[
                      //name:渲染表格中列的数据
                      //classes:设置class的值
                      //resizable：是否可以被重置
                      //editable：单元格是否可编辑
                  {name:"id"},
                  {name:"name",editable:true},
                  {name:"password",resizable:false,editable:true},
                  {name:"phone",editable:true},
                      //edittype可编辑类型
                      //editoptions：可以为edittype渲染初始化数据
                      //formatter： 对于后台的数据再加工
                  {name:"email",editable:true},
                  {name:"ip",editable:true},
                  {name:"rank",editable:true},
                  {name:"status",editable:true,edittype:"select",editoptions:{value:"1:激活;2:冻结"},
                    formatter:function (cellvalue,options,rowObject) {
                        if (cellvalue==1){
                            return "激活";
                        }else {
                            return "冻结";
                        }
                    }
                  
                  },
                  {name:"option",formatter:function (cellvalue,option,rowObject) {
                          return"<a class='btn btn-primary' onclick=\"del('"+rowObject.id+"')\">删除</a>&nbsp;&nbsp;&nbsp;<a class='btn btn-danger' onclick=\"update('"+rowObject.id+"')\">修改</a>"
                      }
                  }
              ]
           }).jqGrid("navGrid","#pager",{edit:true});
                $("#add").click(function() {
                    //给对应的目标JQGRID表格做添加操作
                    $("#table").jqGrid('editGridRow', "new", {
                        height: 400,
                        reloadAfterSubmit: true  //添加过后是否进行刷新
                    });
                });
            //修改操作
            $("#update").click(function() {
                //获取选中行的rowid
                var gr = $("#table").jqGrid('getGridParam', 'selrow');
                if (gr != null)
                //调用修改的方法
                    $("#table").jqGrid('editGridRow', gr, {
                        height : 400,
                        reloadAfterSubmit : true
                    });
                else
                    alert("请选中一行");
            });
            //删除操作
            $("#del").click(function() {
                //改行的ID
                var gr = $("#table").jqGrid('getGridParam', 'selrow');
                if (gr != null)
                    $("#table").jqGrid('delGridRow', gr, {
                        reloadAfterSubmit : true
                    });
                else
                    alert("请选中一行");
            });
        });

        function update(rowid) {
            //获取选中行的rowid
            if (rowid != null)
            //调用修改的方法
                $("#table").jqGrid('editGridRow', rowid, {
                    height : 400,
                    reloadAfterSubmit : true
                });
            else
                alert("请选中一行");
        }

        function del(rowid) {
            //改行的ID
            if (rowid != null)
                $("#table").jqGrid('delGridRow', rowid, {
                    reloadAfterSubmit : true
                });
            else
                alert("请选中一行");
        }

    </script>
</head>
<body>
<table id="table">

</table>
<!--展示分页的DIV-->
<div id="pager"></div>
<button id="add" class="btn btn-danger">添加</button>
<button id="update" class="btn btn-success">修改</button>
<button id="del" class="btn btn-warning">删除</button>
</body>
</html>