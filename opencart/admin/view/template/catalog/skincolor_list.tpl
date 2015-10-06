<?php echo $header; ?><?php echo $column_left; ?>
<div id="content">
  <div class="page-header">
    <div class="container-fluid">
      <div class="pull-right">
 
        <a href="<?php echo $cancel; ?>" data-toggle="tooltip"   class="btn btn-default"><i class="fa fa-reply"></i></a></div>
      <h1><?php echo $heading_title; ?></h1>
      <ul class="breadcrumb">
        <?php foreach ($breadcrumbs as $breadcrumb) { ?>
        <li><a href="<?php echo $breadcrumb['href']; ?>"><?php echo $breadcrumb['text']; ?></a></li>
        <?php } ?>
      </ul>
    </div>
  </div>
  <div class="container-fluid">
 
     <?php if ($error_warning) { ?>
    <div class="alert alert-danger"><i class="fa fa-exclamation-circle"></i> <?php echo $error_warning; ?>
      <button type="button" class="close" data-dismiss="alert">&times;</button>
    </div>
    <?php } ?>
    <?php if ($success) { ?>
    <div class="alert alert-success"><i class="fa fa-check-circle"></i> <?php echo $success; ?>
      <button type="button" class="close" data-dismiss="alert">&times;</button>
    </div>
 <?php } ?>
    <div class="panel panel-default">
        <div class="panel-heading">
          <h3 class="panel-title"><i class="fa fa-pencil"></i><?php echo  $text_list; ?></h3>
       </div>
     <div class="panel-body">
      <form action="<?php echo '$delete'; ?>" method="post" enctype="multipart/form-data" id="form">
       
            
            <table id="slide-float" class="table table-striped table-bordered table-hover">
                <thead>
		   
            <tr>
                <td><?php echo  $skinname; ?></td>
                <td><?php echo  $status; ?></td>    
                <td class="text-right"><?php echo $column_action; ?></td>
            </tr>
          </thead>
          <tbody>
            <?php if ($colorskins) { ?>
               <?php foreach ($colorskins as $colorskin) { ?>
                   <tr>  
	                <td class="left"><?php echo $colorskin['skinname']; ?></td>

	                <td class="left">
			  <?php if ($colorskin['status']==1) { 
			       echo $text_enabled;  
			    } else {  
			      echo $text_disabled; 
			   } ?>
			  </td>
			   
                        <td class="text-right">  
			  <a href="<?php echo $action . '&skin_id=' . $colorskin['skin_id'] ; ?>" class="btn btn-primary"> <i class="fa fa-pencil"></i></a> 
			 </td>
                     </tr>
               <?php } ?>
            <?php } else { ?>
                   <tr><td class="center" colspan="3"><?php echo $text_no_results; ?></td>
                   </tr>
            <?php } ?>
          </tbody>
        </table>
      </form>
    </div>
  </div>
</div>

</div> 
<?php echo $footer; ?>