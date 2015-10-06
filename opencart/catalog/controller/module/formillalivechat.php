<?php
class ControllerModuleFormillalivechat extends Controller {
	public function index() {
		$this->load->language('module/formillalivechat');

		$data['heading_title'] = $this->language->get('heading_title');

		$data['code'] = html_entity_decode($this->config->get('formilla_chat_id'));

		if (file_exists(DIR_TEMPLATE . $this->config->get('config_template') . '/template/module/formillalivechat.tpl')) {
			return $this->load->view($this->config->get('config_template') . '/template/module/formillalivechat.tpl', $data);
		} else {
			return $this->load->view('default/template/module/formillalivechat.tpl', $data);
		}
	}
}