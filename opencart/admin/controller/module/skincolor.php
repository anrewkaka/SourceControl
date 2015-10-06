<?php
class ControllerModuleSkincolor extends Controller {
	private $error = array();

	public function index() {
		$this->load->language('module/skincolor');

		$this->document->setTitle($this->language->get('heading_title'));

	 
		$this->load->model('catalog/colorskin');
		$result = $this->model_catalog_colorskin->chkactive(); 
	 
		$this->model_catalog_colorskin->cleanskin(); 
		

        $files =  glob(DIR_CATALOG . 'view/theme/'  . $this->config->get('config_template') . '/stylesheet/skincolor/*.css') ; 

        if ($files) { 
			foreach ($files as $file) {  
                  
				 $this->model_catalog_colorskin->insertskin(basename($file, '.css'));
				
			}
		} 

	    if (isset($result['skinname'])) {
           
			 $this->model_catalog_colorskin->initial( $result['skinname']);
		}
       
        
		$results = $this->model_catalog_colorskin->Loadskin(); 

		 foreach ($results as $result) {
		        $data['colorskins'][] = array(
				'skin_id' => $result['skin_id'],
				'skinname'            => $result['skinname'],
				'status'            => $result['status']
				 
			);
		}	
      
      
		if (isset($this->error['warning'])) {
			$data['error_warning'] = $this->error['warning'];
		} else {
			$data['error_warning'] = '';
		}

        
		if (isset($this->session->data['success'])) {
			$data['success'] = $this->session->data['success'];

			unset($this->session->data['success']);
		} else {
			$data['success'] = '';
		}

		$data['heading_title'] = $this->language->get('heading_title');
		 
        $data['text_update'] = $this->language->get('text_update');
		$data['text_enabled'] = $this->language->get('text_enabled');
		$data['text_disabled'] = $this->language->get('text_disabled');
		$data['button_save'] = $this->language->get('button_save');
		$data['button_cancel'] = $this->language->get('button_cancel');
		$data['column_action'] = $this->language->get('column_action');
		$data['status'] = $this->language->get('entry_status');
		$data['skinname'] = $this->language->get('text_name');
		$data['text_list'] = $this->language->get('text_list');


		$data['breadcrumbs'] = array();

		$data['breadcrumbs'][] = array(
			'text' => $this->language->get('text_home'),
			'href' => $this->url->link('common/dashboard', 'token=' . $this->session->data['token'], 'SSL')
		);

		$data['breadcrumbs'][] = array(
			'text' => $this->language->get('text_module'),
			'href' => $this->url->link('extension/module', 'token=' . $this->session->data['token'], 'SSL')
		);

		 
		 $data['breadcrumbs'][] = array(
				'text' => $this->language->get('text_list'),
				'href' => $this->url->link('module/skincolor', 'token=' . $this->session->data['token'], 'SSL')
		 );
		 

		$data['action'] = $this->url->link('module/skincolor/edit', 'token=' . $this->session->data['token'], 'SSL');
		$data['cancel'] = $this->url->link('extension/module', 'token=' . $this->session->data['token'], 'SSL'); 
		$data['header'] = $this->load->controller('common/header');
		$data['column_left'] = $this->load->controller('common/column_left');
		$data['footer'] = $this->load->controller('common/footer');
		$this->response->setOutput($this->load->view('catalog/skincolor_list.tpl', $data));
	}


  public function edit() {		
	   $this->load->language('module/skincolor');
 
		$this->document->setTitle($this->language->get('heading_title'));
		$data['heading_title'] = $this->language->get('heading_title');
		$data['text_enabled'] = $this->language->get('text_enabled');
		$data['text_disabled'] = $this->language->get('text_disabled');
		$data['text_edit'] = $this->language->get('text_edit');
        $data['text_status'] = $this->language->get('text_status');

		$data['header'] = $this->load->controller('common/header');
		$data['column_left'] = $this->load->controller('common/column_left');
		$data['footer'] = $this->load->controller('common/footer');

		
		$data['cancel'] = $this->url->link('module/skincolor', 'token=' . $this->session->data['token'], 'SSL'); 
		 	$data['breadcrumbs'] = array();

		$data['breadcrumbs'][] = array(
			'text' => $this->language->get('text_home'),
			'href' => $this->url->link('common/dashboard', 'token=' . $this->session->data['token'], 'SSL')
		);

		$data['breadcrumbs'][] = array(
			'text' => $this->language->get('text_module'),
			'href' => $this->url->link('extension/module', 'token=' . $this->session->data['token'], 'SSL')
		);

		 
		 $data['breadcrumbs'][] = array(
				'text' => $this->language->get('text_edit'),
				'href' => $this->url->link('module/skincolor', 'token=' . $this->session->data['token'], 'SSL')
		 );
		 


        $data['button_save'] = $this->url->link('module/skincolor/edit', 'token=' . $this->session->data['token'] . '&skin_id=' . $this->request->get['skin_id'] , 'SSL');
        $this->load->model('catalog/colorskin');
		$result =$this->model_catalog_colorskin->checkstatus($this->request->get['skin_id']);
		$data['skin_status'] = $result['status'];

		if (($this->request->server['REQUEST_METHOD'] == 'POST')  ) {
		    $this->load->model('catalog/colorskin');
	     	$results = $this->model_catalog_colorskin->updateskin( $this->request->post,$this->request->get['skin_id']); 

			$this->session->data['success'] = $this->language->get('text_success');

			$this->response->redirect($this->url->link('module/skincolor', 'token=' . $this->session->data['token'], 'SSL'));
		}

		$this->response->setOutput($this->load->view('catalog/skincolor_form.tpl', $data));
	}
   
}