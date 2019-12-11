/**
 * 
 */
package com.qhc.frye.rest.controller.qhc;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.qhc.frye.domain.Contract;
import com.qhc.frye.rest.controller.entity.ContractView;
import com.qhc.frye.rest.controller.entity.PageHelper;
import com.qhc.frye.rest.controller.entity.Result;
import com.qhc.frye.service.BestsignService;
import com.qhc.frye.service.ContractService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author Walker
 *
 */
@Controller
@Api(value = "Contract management in Frye", description = "合同管理")
@RequestMapping(path = "contract")
public class ContractController {
	Logger logger = LoggerFactory.getLogger(ContractController.class);

	@Autowired
	private ContractService contractService;

	@Autowired
	private BestsignService bestsignService;

	@Value("${contract.bestsign.contractDir}")
	private String contractDir;
	
	@ApiOperation(value = "查询合同列表")
	@GetMapping(value = "")
	@ResponseBody
	public Result<? extends Object> find(@RequestParam(required = false) Map<String, Object> params) throws Exception {
		Result<? extends Object> result = null;
		try {
			PageHelper<ContractView> page = contractService.find(params);
			result = Result.ok(page);
		} catch (Exception e) {
			logger.error("查询合同", e);
			result = Result.error(e.getMessage());
		}
		return result;
	}
	
	@ApiOperation(value = "保存或修改合同",notes="保存或修改合同")
	@PostMapping(value = "")
	@ResponseBody
	public Result<? extends Object> save(@RequestBody(required = true) ContractView view ) {
		Result<? extends Object> result = null;
		try {
			Contract contract = new Contract();
			BeanUtils.copyProperties(view, contract);
			contract = contractService.save(contract);
			view.setId(contract.getId());
			result = Result.ok(view);
		} catch (Exception e) {
			logger.error("保存或修改合同", e);
			result = Result.error(e.getMessage());
		}
		return result;
	}
	
	@ApiOperation(value = "获取合同详情",notes="获取合同详情")
	@GetMapping(value = "{id}")
	@ResponseBody
	public Result<? extends Object> getContract(@PathVariable("id") Integer contractId) {
		Result<? extends Object> result = null;
		try {
			ContractView c = contractService.findById(contractId);
			result = Result.ok(c);
		} catch (Exception e) {
			String msg = "查询合同详情，合同ID=" + contractId;
			logger.error(msg, e);
			result = Result.error(e.getMessage());
		}
		return result;
	}
	
	@ApiOperation(value = "更新合同状态",notes="更新合同状态")
	@PutMapping(value = "{id}/status/{status}")
	@ResponseBody
	public Result<?> updateContractStatus(@PathVariable("id") Integer contractId, @PathVariable("status") Integer status) {
		Result<?> result = null;
		try {
			Contract c = contractService.findOne(contractId);
			c.setStatus(status);
			contractService.save(c);
			result = Result.ok(c);
		} catch (Exception e) {
			String msg = "更新合同状态，合同ID=" + contractId + "， status=" + status;
			logger.error(msg, e);
			result = Result.error(e.getMessage());
		}
		return result;
	}
	
	@ApiOperation(value = "更新合同PDF文档hashcode信息",notes="更新合同PDF文档hashcode信息")
	@PutMapping(value = "{id}/hashcode/{hashcode}")
	@ResponseBody
	public Result<?> updateContractFileHashcode(@PathVariable("id") Integer contractId, @PathVariable("hashcode") String hashcode) {
		Result<?> result = null;
		try {
			Contract c = contractService.findOne(contractId);
			c.setFileHashCode(hashcode);
			contractService.save(c);
			result = Result.ok(c);
		} catch (Exception e) {
			String msg = "更新合同PDF文档hashcode信息，合同ID=" + contractId + "， FileHashCode=" + hashcode;
			logger.error(msg, e);
			result = Result.error(e.getMessage());
		}
		return result;
	}
	
	@ApiOperation(value = "更新合同电子签约合同ID信息",notes="更新合同电子签约合同ID信息")
	@PutMapping(value = "{id}/signid/{signContractId}/{status}")
	@ResponseBody
	public Result<? extends Object> updateContractSignId(@PathVariable("id") Integer contractId, @PathVariable("signContractId") String signContractId, @PathVariable("status") Integer status) {
		Result<? extends Object> result = null;
		try {
			Contract c = contractService.findOne(contractId);
			c.setSignContractId(signContractId);
			c.setStatus(status);
			contractService.save(c);
			result = Result.ok(c);
		} catch (Exception e) {
			String msg = "更新合同电子签约合同ID信息，合同ID=" + contractId + "， SignContractId=" + signContractId;
			logger.error(msg, e);
			result = Result.error(e.getMessage());
		}
		return result;
	}

	/**
	 * 
	 * 手工刷新电子签约合同状态
	 * 
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = { "refreshState" }, method = { RequestMethod.PUT })
	@ResponseBody
	public Result<?> refreshState() throws JsonProcessingException {
		Result<?> r = null;
		boolean flag = this.contractService.doRefreshContractState();
		if (flag) {
			r = Result.ok("");
		} else {
			r = Result.error("");
		}
		return r;
	}

	/**
	 * 签署合同
	 * 
	 * @param id
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = { "{id}/sign" }, method = { RequestMethod.PUT })
	@ResponseBody
	public Result<?> signContract(@PathVariable("id") Integer contractId) throws JsonProcessingException {
		Result<?> r = null;
		boolean flag = this.contractService.doSignContract(contractId);
		if (flag) {
			r = Result.ok("");
		} else {
			r = Result.error("");
		}
		return r;
	}

	/**
	 * 从上上签下载电子签约合同文档
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping({ "{id}/download" })
	public void downloadContract(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String idStr = request.getParameter("id");
		ContractView contract = this.contractService.findById(Integer.valueOf(idStr));

		String signContractId = contract.getSignContractId();
		byte[] zipBytes = this.bestsignService.downloadContract(signContractId);

		String time = String.valueOf(System.currentTimeMillis());
		String path = this.contractDir + contract.getSequenceNumber() + "_" + contract.getContractorName() + "_" + time
				+ ".zip";
		ZipFile zf = null;
		InputStream in = null;
		ZipInputStream zin = null;
		InputStream pin = null;
		try {
			Files.write(Paths.get(path, new String[0]), zipBytes, new java.nio.file.OpenOption[0]);
			zf = new ZipFile(path);
			in = new BufferedInputStream(new FileInputStream(path));
			zin = new ZipInputStream(in);
			ZipEntry ze;
			while ((ze = zin.getNextEntry()) != null) {
				if (ze.toString().endsWith(".pdf")) {
					pin = new BufferedInputStream(zf.getInputStream(ze));
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		String customerName = "";
		if (contract.getContractorName() != null) {
			customerName = new String(contract.getContractorName().getBytes("gb2312"), "ISO8859-1");
		}
		String pdfFileName = contract.getSequenceNumber() + "-" + customerName + "(" + contract.getContractNumber()
				+ ").pdf";
		if (contract.getVersion() != null && !contract.getVersion().isEmpty())
			pdfFileName = contract.getSequenceNumber() + "-" + customerName + "(" + contract.getContractNumber() + "-"
					+ contract.getVersion() + ").pdf";
		response.setContentType("application/x-download;charset=GB2312");
		response.setHeader("Content-disposition", "attachment;filename=" + pdfFileName);

		byte[] b = new byte[1024];
		int len = -1;
		while ((len = pin.read(b, 0, 1024)) != -1) {
			response.getOutputStream().write(b, 0, len);
		}

		try {
			if (in != null)
				pin.close();
			if (zin != null)
				zin.closeEntry();
			if (in != null)
				in.close();
			if (zf != null)
				zf.close();
			(new File(path)).delete();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

}
