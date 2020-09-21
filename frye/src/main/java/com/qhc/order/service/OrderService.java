package com.qhc.order.service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.qhc.Constant;
import com.qhc.order.domain.CharacteristicDto;
import com.qhc.order.domain.ContractDto;
import com.qhc.order.domain.DeliveryAddressDto;
import com.qhc.order.domain.ItemDto;
import com.qhc.order.domain.Mail;
import com.qhc.order.domain.OrderDto;
import com.qhc.order.domain.OrderOption;
import com.qhc.order.domain.OrderQuery;
import com.qhc.order.domain.OrderVersion;
import com.qhc.order.domain.bpm.BpmOrder;
import com.qhc.order.domain.bpm.OrderAttachment;
import com.qhc.order.domain.bpm.OrderHeader;
import com.qhc.order.domain.bpm.OrderItem;
import com.qhc.order.domain.bpm.OrderMargin;
import com.qhc.order.domain.sap.SapOrderStatus;
import com.qhc.order.entity.Attachment;
import com.qhc.order.entity.BillingPlan;
import com.qhc.order.entity.BpmDicision;
import com.qhc.order.entity.Characteristics;
import com.qhc.order.entity.DeliveryAddress;
import com.qhc.order.entity.Item;
import com.qhc.order.entity.ItemAttachment;
import com.qhc.order.entity.ItemColor;
import com.qhc.order.entity.Order;
import com.qhc.order.entity.OrderInfo;
import com.qhc.order.entity.SpecialOrderApplication;
import com.qhc.order.mapper.AttachmentMapper;
import com.qhc.order.mapper.BillingPlanMapper;
import com.qhc.order.mapper.BpmDicisionMapper;
import com.qhc.order.mapper.CharacteristicsMapper;
import com.qhc.order.mapper.DeliveryAddressMapper;
import com.qhc.order.mapper.ItemAttachmentMapper;
import com.qhc.order.mapper.ItemColorMapper;
import com.qhc.order.mapper.ItemMapper;
import com.qhc.order.mapper.OrderInfoMapper;
import com.qhc.order.mapper.OrderMapper;
import com.qhc.order.mapper.SpecialOrderApplicationMapper;
import com.qhc.sap.dao.MaterialRepository;
import com.qhc.sap.dao.PaymentTermRepository;
import com.qhc.sap.dao.SalesTypeRepository;
import com.qhc.sap.domain.Characteristic;
import com.qhc.sap.domain.CustomerDto;
import com.qhc.sap.domain.MaterialDto;
import com.qhc.sap.entity.IndustryCode;
import com.qhc.sap.entity.Material;
import com.qhc.sap.entity.MaterialGroups;
import com.qhc.sap.entity.PaymentTerm;
import com.qhc.sap.entity.SalesType;
import com.qhc.sap.entity.Tax;
import com.qhc.sap.mapper.SapOrderMapper;
import com.qhc.sap.mapper.SapViewMapper;
import com.qhc.sap.mapper.TaxMapper;
import com.qhc.sap.service.MaterialService;
import com.qhc.sap.service.SapService;
import com.qhc.system.dao.AreaRepository;
import com.qhc.system.dao.CityRepository;
import com.qhc.system.dao.ProvinceRepository;
import com.qhc.system.dao.SettingsRepository;
import com.qhc.system.entity.Area;
import com.qhc.system.entity.City;
import com.qhc.system.entity.Province;
import com.qhc.system.entity.Settings;
import com.qhc.system.entity.User;
import com.qhc.system.mapper.UserMapper;
import com.qhc.system.service.SettingsService;
import com.qhc.system.service.UserService;

@Service
public class OrderService {
  private static Logger logger = LoggerFactory.getLogger(OrderService.class);

  @Value("${sap.paymentplan.addr}")
  String paymentplanUrlStr;

  @Value("${sap.sapCreateOrder.addr}")
  private String orderCreationUrl;

  @Value("${sap.sapChangeOrder.addr}")
  private String orderChangeUrl;

  @Autowired
  private OrderInfoMapper orderInfoMapper;

  @Autowired
  private OrderMapper orderMapper;

  @Autowired
  private BpmDicisionMapper dicisionMapper;

  @Autowired
  private BillingPlanMapper billingPlanMapper;

  @Autowired
  private AttachmentMapper attachmentMapper;

  @Autowired
  private DeliveryAddressMapper deliveryAddressMapper;

  @Autowired
  private ItemMapper itemMapper;

  @Autowired
  private ItemAttachmentMapper itemAttachmentMapper;

  @Autowired
  private CharacteristicsMapper characteristicsMapper;

  @Autowired
  private ItemColorMapper itemColorMapper;

  @Autowired
  private SapViewMapper sapViewMapper;

  @Autowired
  private SalesTypeRepository salesTypeRepo;

  @Autowired
  private AreaRepository districtRepo;

  @Autowired
  private CityRepository cityRepo;

  @Autowired
  private ProvinceRepository provinceRepo;

  @Autowired
  private SalesTypeRepository saleTypeRepo;

  @Autowired
  private PaymentTermRepository paymentRepo;

  @Autowired
  private ConstantService constService;

  @Autowired
  private SettingsRepository settingsRepository;

  @Autowired
  private UserService userService;

  @Autowired
  private MaterialService materialService;

  @Autowired
  private MaterialRepository materialRepo;

  @Autowired
  private BpmService bpmService;

  @Autowired
  private GrossProfitMarginService grossProfitMarginService;

  @Autowired
  private SpecialOrderApplicationMapper specialOrderApplicationMapper;

  @Autowired
  private SettingsService settingsService;

  @Autowired
  private SapOrderService sapOrderService;

  @Autowired
  private SapService sapService;

  @Autowired
  private SapOrderMapper sapOrderMapper;
  
  @Autowired
  private MailService mailService;

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private TaxMapper taxMapper;
  
  @Autowired
  private ContractService contractService;
  
  @Transactional
  public OrderDto save(String user, final OrderDto orderDto) throws Exception {
    orderDto.setUpdater(user);
    List<ItemDto> items = orderDto.getItems();
    String stOrderType = orderDto.getStOrderType();

    if (StringUtils.isEmpty(orderDto.getStatus())) {
      orderDto.setStatus(OrderDto.ORDER_STATUS_DRAFT);
    }

    if (orderDto.getStatus().equals(OrderDto.ORDER_STATUS_MANAGER)) {
      orderDto.setContractManager(user);
    }
    
    // 转换折扣数据存储格式，48.0 -> 0.480
    convertDiscountToDecimal(orderDto);

    // 报价单状态
    if (stOrderType.equals("3")) {
      if (StringUtils.isEmpty(orderDto.getQuoteStatus())) {
        orderDto.setQuoteStatus("00");
      }
    }

    // 檢查空值
    if (orderDto.getAdditionalFreight() == null) {
      orderDto.setAdditionalFreight(0D);
    }
    
    for (ItemDto item : items) {
      if (StringUtils.isEmpty(item.getItemStatus())) {
        item.setItemStatus("00");
      }
      // 冷库，将订单头冷库费用设置到冷库物料行项目的转移价和标准价
      if (item.getMaterialCode().equals("BG1R8R00000-X")) {
        double refrigeratoryFee = ObjectUtils.defaultIfNull(orderDto.getRefrigeratoryFee(), 0d);
        item.setStandardPrice(refrigeratoryFee);
        // 冷库转移价=冷库费用*1.05
        Settings rateSetting = settingsService.findByCode("refrigeratory_transaction_rate");
        double refrigeratoryTransactionRate = Double.valueOf(rateSetting.getsValue());
        item.setTransactionPrice(refrigeratoryFee * refrigeratoryTransactionRate);
      }
    }

    // 檢查合同號
    orderDto.setContractNumber(StringUtils.trimToEmpty(orderDto.getContractNumber()));
    checkContractNumber(orderDto.getOrderId(), orderDto.getContractNumber());

    // is b2c
    if (items != null) {
      items.forEach(i -> {
        String status = i.getItemStatus();
        // 取消状态的行项目不在累计范围
        if (StringUtils.isNotEmpty(i.getB2cComments()) && !status.equals("Z2")) {
          orderDto.setIsB2c(1);
        }
      });
    }

    // 经销商非标折扣订单
    if (stOrderType.equals("2")) {
      // 特批折扣
//      orderDto.setIsSpecial(1);
      // 计算合并折扣和行项目金额
     //  this.calcMergeDiscount(orderDto);
    }

    // calculate gross profit margin
    List<MaterialGroups> margin = grossProfitMarginService.calculate(orderDto);
    orderDto.setGrossProfitMargin(new ObjectMapper().writeValueAsString(margin));

    Order order = new Order();
    OrderInfo orderInfo = new OrderInfo();
    BeanUtils.copyProperties(order, orderDto);
    order.setId(orderDto.getOrderId());
    BeanUtils.copyProperties(orderInfo, orderDto);

    if (orderDto.getOrderId() == null || orderDto.getOrderId() == 0) {
        // 由前台生成
        // String sequenceNumber = "QHC" + version;
        // order.setSequenceNumber(sequenceNumber.toUpperCase());
        orderMapper.insert(order);
        orderDto.setOrderId(order.getId());
        orderInfo.setOrderId(order.getId());
    } else {
        orderMapper.update(order);
    }
    if (orderDto.getId() == null || orderDto.getId() == 0) {
        if (StringUtils.trimToEmpty(orderInfo.getVersion()).length() == 0) {
            // new version
            String version = new SimpleDateFormat("yyyyMMddHHssmmSSS").format(new Date());
            orderInfo.setVersion(version.toUpperCase());
        }
        if (orderInfo.getVersionNum() == null || orderInfo.getVersionNum() == 0) {
            orderInfo.setVersionNum(1);
        }
        orderInfo.setIsActive(1);

      orderInfoMapper.insert(orderInfo);
      orderDto.setId(orderInfo.getId());
    } else {
      orderInfoMapper.update(orderInfo);
    }

    saveAttachments(orderDto);
    saveBillingPlans(orderDto);
    saveDeliveryAddresses(orderDto);

    saveItems(orderDto);

    OrderDto dto = this.findOrder(orderInfo.getId());

    return dto;
  }

  /**
   * 转换折扣数据存储格式，48.0 -> 0.480
   * @param orderDto
   */
  private void convertDiscountToDecimal(final OrderDto orderDto) {
    orderDto.setApprovedBodyDiscount(orderDto.getApprovedBodyDiscount() / 100);
    orderDto.setApprovedMainDiscount(orderDto.getApprovedMainDiscount() / 100);
    orderDto.setBodyDiscount(orderDto.getBodyDiscount() / 100);
    orderDto.setMainDiscount(orderDto.getMainDiscount() / 100);
    orderDto.setDiscount(orderDto.getDiscount() / 100);
    List<ItemDto> items = orderDto.getItems();
    if (items != null && items.size() > 0) {
      items.forEach(e -> {
        e.setDiscount(e.getDiscount() / 100);
      });
    }
  }

  /**
   * 删除订单
   * 
   * @param user
   * @param orderInfoId
   * @return
   */
  public void delete(String user, Integer orderInforId) {
    OrderInfo orderInfo = orderInfoMapper.findById(orderInforId);
    if (orderInfo == null) {
      throw new RuntimeException("订单不存在-" + orderInforId);
    }
    Integer orderId = orderInfo.getOrderId();
    Order order = orderMapper.findById(orderId);
    String status = orderInfo.getStatus();
    switch (status) {
      case OrderDto.ORDER_STATUS_DRAFT:
      case OrderDto.ORDER_STATUS_REJECT:
        int versionNum = orderInfo.getVersionNum();
        deleteItems(orderInforId);
        attachmentMapper.deleteByOrderInfoId(orderInforId);
        billingPlanMapper.deleteByOrderInfoId(orderInforId);
        deliveryAddressMapper.deleteByOrderInfoId(orderInforId);
        orderInfoMapper.deleteById(orderInforId);
        if (versionNum > 1) {
          // 将最后一版本的active设置为1
          Map<String, Object> params = new HashMap<String, Object>() {
            {
              put("orderId", orderId);
              put("orderByClause", "create_time desc");
            }
          };
          List<OrderInfo> historys = orderInfoMapper.findByParams(params);
          if (historys != null && historys.size() > 0) {
            OrderInfo last = historys.get(0);
            last.setIsActive(1);
            orderInfoMapper.setActive(last.getId(), 1);
          }
        } else {
          // 删除订单
          orderMapper.deleteById(orderId);
          // 如果是直签订单并且是报价单下单来的，删除时恢复原报价单状态为草稿
          if (order.getStOrderType().equals("4") && order.getQuoteOrderId() != null) {
            Order quoteOrder = orderMapper.findById(order.getQuoteOrderId());
            quoteOrder.setQuoteStatus("00");
            orderMapper.update(quoteOrder);
          }
        }
        break;
      default:
        throw new RuntimeException("订单当前状态【" + status + "】不可删除");
    }
  }

  /**
   * 檢查合同號
   * 
   * @param orderDto
   */
  private void checkContractNumber(Integer orderId, String contractNumber) {
    if (contractNumber.length() > 0) {
      contractNumber = contractNumber.toUpperCase();
      if (contractNumber.length() > 10) {
        throw new RuntimeException("合同号已存在或格式不正确");
      }
      Map<String, Object> params = new HashMap<>();
      params.put("contractNumber", contractNumber);
      int count = sapOrderMapper.countByParams(params);
      if (count > 0) {
        throw new RuntimeException("合同号已存在或格式不正确");
      }
      params.clear();
      params.put("orderId", orderId);
      params.put("contractNumber", contractNumber);
      List<String> existsConstractNumberList = orderInfoMapper.checkContractNumber(params);
      if (existsConstractNumberList.size() > 0) {
        throw new RuntimeException("合同号已存在或格式不正确");
      }
    }
  }

  /**
   * 订单变更，BPM审批通过后的订单修改，产生新的版本
   * 
   * @param user
   * @param orderInfoId
   * @return
   * @throws Exception
   */
  @Transactional
  public OrderDto upgrade(String user, Integer orderInfoId) throws Exception {
    OrderDto order = this.findOrder(orderInfoId);
    String status = order.getStatus();

    if (!(OrderDto.ORDER_STATUS_APPROVED.equals(status)
        || OrderDto.ORDER_STATUS_APPROVED_UPDATE.equals(status)
        || OrderDto.ORDER_STATUS_SAP.equals(status))) {
      throw new RuntimeException("订单当前状态不允许变更");
    }

    // 将其他版本的active设置为0
    orderInfoMapper.inactive(order.getOrderId());

    order.setId(null);
    // order.setCreater(user);
    order.setUpdater(user);
    // new version
    String version = new SimpleDateFormat("yyyyMMddHHssmmSSS").format(new Date());
    order.setVersion(version);
//    String sequenceNumber = "QHC" + version;
//    order.setSequenceNumber(sequenceNumber);
    order.setIsActive(1);
    order.setVersionNum(order.getVersionNum() + 1);
    order.setStatus(OrderDto.ORDER_STATUS_DRAFT);
    order.setSubmitBpmTime(null);
    order.setSubmitTime(null);

    order = this.save(user, order);

    return order;
  }

  /**
   * 复制订单
   * 
   * @param user
   * @param orderInfoId
   * @return
   * @throws Exception
   */
  @Transactional
  public OrderDto copy(String user, Integer orderInfoId) throws Exception {
    OrderDto order = this.findOrder(orderInfoId);

    order.setId(null);
    order.setOrderId(null);
    order.setCreater(user);
    order.setSalesCode(user);
    order.setUpdater(user);
    // order.setAttachments(null); // 清除订单附件
    order.setContractNumber(""); // 合同號
    // 直签订单清空安装费等5个费用
    if (order.getStOrderType().equals("3") || order.getStOrderType().equals("4")) {
    	/* 工程安装费 */
    	order.setInstallFee(null);
    	/* 工程材料费 */
    	order.setMaterialFee(null);
    	/* 工程电气费 */
    	order.setElectricalFee(null);
    	/* 工程冷库费 */
    	order.setRefrigeratoryFee(null);
    	/* 工程维保费 */
    	order.setMaintenanceFee(null);
    }
    for (ItemDto item : order.getItems()) {
      // item.setAttachments(null); // 清除调研表附件
      item.setItemStatus("00");
      
      String materialCode = item.getMaterialCode();
      // 特殊物料不处理
      if (Constant.specialMaterials.contains(materialCode)) {
          continue;
      }
      // 同步物料最新价格
//  	MaterialDto materialDto = materialService.getMaterialsById(materialCode, order.getCustomerIndustry());
//      syncItemPrice(item, materialDto);
      
      List<CharacteristicDto> configs = item.getConfigs();
      // 没有特征值跳过
      if (configs == null) {
          configs = new ArrayList<>();
      }
      // 同步行项目特征
      configs = syncItemCharacter(materialCode, configs);
      item.setConfigs(configs);
    }
    // new version
    String version = new SimpleDateFormat("yyyyMMddHHssmmSSS").format(new Date());
    order.setVersion(version);
    String sequenceNumber = "QHC" + version;
    order.setSequenceNumber(sequenceNumber.toUpperCase());
    order.setIsActive(1);
    order.setVersionNum(1);
    order.setStatus(OrderDto.ORDER_STATUS_DRAFT);
    order.setSubmitBpmTime(null);
    order.setSubmitTime(null);

    order = this.save(user, order);

    return order;
  }

  /**
   * 同步物料价格
   * @param item
   * @param materialDto
   * @return
   */
  private MaterialDto syncItemPrice(ItemDto item, MaterialDto materialDto) {
      item.setStandardPrice(materialDto.getStandardPrice());
      item.setTransactionPrice(materialDto.getTranscationPrice());
      item.setRetailPrice(materialDto.getRetailPrice());
      item.setOptionalStandardPrice(ObjectUtils.defaultIfNull(item.getOptionalStandardPrice(), 0D));
      // item.setOptionalTransactionPrice(ObjectUtils.defaultIfNull(item.getOptionalStandardPrice(), 0D) * materialDto.getTranscationPrice() / materialDto.getStandardPrice());
      item.setOptionalTransactionPrice(ObjectUtils.defaultIfNull(item.getOptionalTransactionPrice(), 0D));
      item.setOptionalRetailPrice(item.getOptionalRetailPrice());
      if (Constant.freeCategorys.contains(item.getItemCategory())) {
          // 免费
          item.setActualPrice(0);
          item.setOptionalActualPrice(0);
      } else {
          // 查询出来的订单行项目折扣为百分比形式， ex. 48
          item.setActualPrice(item.getRetailPrice() * item.getDiscount() / 100);
          item.setOptionalActualPrice(ObjectUtils.defaultIfNull(item.getOptionalRetailPrice(), 0D) * item.getDiscount() / 100);
      }
	return materialDto;
}
  
  /**
   * 同步物料特征表
   * @param materialCode
   * @param oldConfigs
   * @return
   * @throws Exception
   */
  private List<CharacteristicDto> syncItemCharacter(String materialCode, List<CharacteristicDto> oldConfigs) throws Exception {
      List<CharacteristicDto> tempConfigs = new ArrayList<>();
      List<Characteristic> characs = materialService.getCharactersByClazzCode(materialCode);
      if (characs != null && characs.size() > 0) {
          for (Characteristic c : characs) {
            boolean configurable = c.getConfigs() != null && c.getConfigs().size() > 0;
            boolean isColor = c.isColor();
            CharacteristicDto characDto = new CharacteristicDto();
            tempConfigs.add(characDto);
            characDto.setColor(isColor);
            characDto.setConfigurable(configurable);
            characDto.setKeyCode(c.getCode());
            characDto.setKeyName(c.getName());
            characDto.setOptional(c.isOptional());
            characDto.setValueCode("");
            characDto.setValueName("");
            oldConfigs.forEach(e -> {
                // 如果原来的行项目调研表有相同特征，复制原有特征的特征值
                if (e.getKeyCode().equals(c.getCode())) {
                    characDto.setValueCode(e.getValueCode());
                    characDto.setValueName(e.getValueName());
                }
            });
            // 如果原来的行项目调研表没有有相同特征，或值为空，取默认特征值
            if (StringUtils.trimToEmpty(characDto.getValueCode()).length() == 0) {
                c.getConfigs().forEach(e -> {
                  if (e.isDefault()) {
                    characDto.setValueCode(e.getCode());
                    characDto.setValueName(e.getName());
                  }
                });
            }
            // 如果是新的特征并且默认特征值为空，设置为特殊值，以便能够保存
            if (StringUtils.trimToEmpty(characDto.getValueCode()).equals("-") || StringUtils.trimToEmpty(characDto.getValueCode()).equals("")) {
                characDto.setValueCode("UNKNOWN");
            }
          }
        }

        return tempConfigs;
  }

  /**
   * 报价单下订单
   * 
   * @param user
   * @param orderInfoId
   * @return
   * @throws Exception
   */
  @Transactional
  public OrderDto transfer(String user, Integer orderInfoId) throws Exception {
    OrderDto order = this.findOrder(orderInfoId);
    String stOrderType = order.getStOrderType();
    String quoteStatus = order.getQuoteStatus();
    String status = order.getStatus();
    if (!stOrderType.equals("3")) {
      throw new RuntimeException("非直签客户投标报价单，不允许下单");
    }
    if (!quoteStatus.equals("01")) {
      throw new RuntimeException("还未中标，不允许下单");
    }
    if (!status.equals(OrderDto.ORDER_STATUS_APPROVED)
        && !status.equals(OrderDto.ORDER_STATUS_APPROVED_UPDATE)) {
      throw new RuntimeException("报价单未审批通过，不允许下单");
    }

    // 下单后的订单保存报价单的orderId，删除时恢复原报价单状态为已中标
    order.setQuoteOrderId(order.getOrderId());
    order.setId(null);
    order.setOrderId(null);
    order.setStOrderType("4"); // 下单，转为【直签客户下定单】
    // order.setAttachments(null); // 清除订单附件
    for (ItemDto item : order.getItems()) {
      // item.setAttachments(null); // 清除调研表附件
      item.setItemStatus("00");
    }
    // 下推到SAP的签约人应为直签投标报价订单最开始的创建者
//    order.setCreater(user); 
    order.setUpdater(user);
    // new version
    String version = new SimpleDateFormat("yyyyMMddHHssmmSSS").format(new Date());
    order.setVersion(version);
    String sequenceNumber = "QHC" + version;
    order.setSequenceNumber(sequenceNumber.toUpperCase());
    order.setStatus(OrderDto.ORDER_STATUS_DRAFT);
    order.setSubmitBpmTime(null);
    order.setSubmitTime(null);
    order.setIsActive(1);
    order.setVersionNum(1);
    order.setContractNumber("");

    order = this.save(user, order);

    this.updateQuoteStatus(user, orderInfoId, "02"); // 修改报价单报价状态

    return order;
  }

  /**
   * 保存订单附件信息
   * 
   * @param order
   */
  private void saveAttachments(OrderDto orderDto) {
    Integer orderInfoId = orderDto.getId();
    attachmentMapper.deleteByOrderInfoId(orderInfoId);
    List<Attachment> attachments = orderDto.getAttachments();
    if (attachments == null || attachments.size() == 0) {
      return;
    }
    for (Attachment attachment : attachments) {
      attachment.setId(null);
      attachment.setOrderInfoId(orderInfoId);

      attachmentMapper.insert(attachment);
    }
  }

  private void saveBillingPlans(OrderDto orderDto) {
    Integer orderInfoId = orderDto.getId();
    billingPlanMapper.deleteByOrderInfoId(orderInfoId);
    List<BillingPlan> payments = orderDto.getPayments();
    if (payments == null || payments.size() == 0) {
      return;
    }
    for (BillingPlan billingPlan : payments) {
      billingPlan.setId(null);
      billingPlan.setOrderInfoId(orderInfoId);

      billingPlanMapper.insert(billingPlan);
    }
  }

  private void saveDeliveryAddresses(OrderDto orderDto) throws Exception {
    Integer orderInfoId = orderDto.getId();
    deliveryAddressMapper.deleteByOrderInfoId(orderInfoId);
    List<DeliveryAddressDto> addresses = orderDto.getDeliveryAddress();
    if (addresses == null || addresses.size() == 0) {
      return;
    }
    for (DeliveryAddressDto addressDto : addresses) {
      DeliveryAddress address = new DeliveryAddress();

      BeanUtils.copyProperties(address, addressDto);

      address.setId(null);
      address.setOrderInfoId(orderInfoId);

      deliveryAddressMapper.insert(address);
    }
  }

  private void saveItems(OrderDto orderDto) throws Exception {
    Integer orderInfoId = orderDto.getId();
    // 先刪除訂單所有行項目及關聯表數據
    deleteItems(orderInfoId);
    List<ItemDto> items = orderDto.getItems();
    if (items == null || items.size() == 0) {
      return;
    }
    for (ItemDto itemDto : items) {
      String materialCode = itemDto.getMaterialCode();
      // BG1GD1000000-X 其他项目收费，成本固定1元，清除用户输入的标准价和转移价
      if (materialCode.equals("BG1GD1000000-X")) {
        itemDto.setTransactionPrice(0);
        itemDto.setStandardPrice(0);
      }
      // BG1R8R00000-X 冷库
      // BG1R8L00000-X 不可预估费
      // 设置标准价=用户输入的转移价
      if (materialCode.equals("BG1R8R00000-X") || materialCode.equals("BG1R8L00000-X")) {
        itemDto.setStandardPrice(itemDto.getTransactionPrice());
      }

      Item item = new Item();

      BeanUtils.copyProperties(item, itemDto);

      item.setId(null);
      item.setOrderInfoId(orderInfoId);

      itemMapper.insert(item);

      Material m = materialRepo.findById(materialCode).get();
      // 可配置物料，保存颜色特征和物料特征
      if (m.isConfigurable()) {
        saveItemCharacteristic(itemDto, item);
      }
    }
  }

  // 保存行项目特征及附件
  private void saveItemCharacteristic(ItemDto itemDto, Item item) throws Exception {
    String colorOptions = "";
    List<CharacteristicDto> configs = itemDto.getConfigs();
    if (configs == null || configs.size() == 0) {
      configs = new ArrayList<>();
      itemDto.setConfigs(configs);
      // 没有配置调研表，取默认值颜色选项和物料特征
      List<Characteristic> characs =
          materialService.getCharactersByClazzCode(itemDto.getMaterialCode());
      if (characs != null && characs.size() > 0) {
        for (Characteristic c : characs) {
          boolean configurable = c.getConfigs() != null && c.getConfigs().size() > 0;
          boolean isColor = c.isColor();
          CharacteristicDto characDto = new CharacteristicDto();
          configs.add(characDto);
          characDto.setColor(isColor);
          characDto.setConfigurable(configurable);
          characDto.setKeyCode(c.getCode());
          characDto.setKeyName(c.getName());
          characDto.setOptional(c.isOptional());
          characDto.setValueCode("");
          characDto.setValueName("");
          c.getConfigs().forEach(e -> {
            if (e.isDefault()) {
              characDto.setValueCode(e.getCode());
              characDto.setValueName(e.getName());
            }
          });
        }
      }
    }

    for (CharacteristicDto dto : configs) {
      if (dto.isColor()) {
        ItemColor itemColor = new ItemColor();
        itemColor.setItemId(item.getId());
        itemColor.setPaintingClass(dto.getKeyCode());
        itemColor.setColorCode(dto.getValueCode());

        itemColorMapper.insert(itemColor);
        colorOptions += "," + itemColor.getPaintingClass() + ":" + itemColor.getColorCode();
      } else {
        Characteristics c = new Characteristics();
        c.setItemId(item.getId());
        c.setIsConfigurable(dto.isConfigurable() ? 1 : 0);
        c.setKeyCode(dto.getKeyCode());
        // 如果特征值选择为"-"
        if (dto.getValueCode().equals("-")) {
          dto.setValueCode("");
        }
        if (StringUtils.trimToEmpty(dto.getValueCode()).length() == 0) {
          throw new RuntimeException("行号【" + itemDto.getRowNum() + "】物料【" + itemDto.getMaterialName() + "】特征【" + dto.getKeyCode() + "】没有选择特征值");
        }
        c.setValueCode(dto.getValueCode());

        characteristicsMapper.insert(c);
      }
    }
    colorOptions = colorOptions.length() > 0 ? colorOptions.substring(1) : colorOptions;
    item.setColorOptions(colorOptions);
    itemMapper.update(item);

    // 保存调研表附件
    List<ItemAttachment> attachments = itemDto.getAttachments();
    if (attachments != null && attachments.size() > 0) {
      for (ItemAttachment itemAttachment : attachments) {
        itemAttachment.setItemId(item.getId());
        itemAttachment.setOrderInfoId(item.getOrderInfoId());
        itemAttachmentMapper.insert(itemAttachment);
      }
    }
  }

  private void deleteItems(Integer orderInfoId) {
    // delete k_item_color
    this.itemColorMapper.deleteByOrderInfoId(orderInfoId);

    // delete k_characteristics
    this.characteristicsMapper.deleteByOrderInfoId(orderInfoId);

    // delete item attachemtn
    itemAttachmentMapper.deleteByOrderInfoId(orderInfoId);

    // delete order item
    itemMapper.deleteByOrderInfoId(orderInfoId);
  }

  /**
   * 提交订单
   * 
   * @param order
   * @throws Exception
   */
  @Transactional
  public void submit(String user, OrderDto order) throws Exception {
      // 同步行项目物料最新特征
      for (ItemDto item : order.getItems()) {
          List<CharacteristicDto> configs = item.getConfigs();
          // 没有特征值跳过
          if (configs == null) {
              configs = new ArrayList<>();
          }
          // 同步行项目特征
          configs = syncItemCharacter(item.getMaterialCode(), configs);
          item.setConfigs(configs);
    }
    order = save(user, order);
    String status = order.getStatus();
    String stOrderType = order.getStOrderType();
    switch (status) {
      case OrderDto.ORDER_STATUS_DRAFT:
      case OrderDto.ORDER_STATUS_REJECT:
        if (order.getIsB2c() == 1) {
          order.setStatus(OrderDto.ORDER_STATUS_B2C);
        } else if (stOrderType.equals("3") || stOrderType.equals("4")) {
          // 直销客户订单和直销客户报价单
          order.setStatus(OrderDto.ORDER_STATUS_ENGINER);
        } else {
          order.setStatus(OrderDto.ORDER_STATUS_MANAGER);
        }
        break;
      case OrderDto.ORDER_STATUS_B2C:
        if (stOrderType.equals("3") || stOrderType.equals("4")) {
          // 直销客户订单和直销客户报价单
          order.setStatus(OrderDto.ORDER_STATUS_ENGINER);
        } else {
          order.setStatus(OrderDto.ORDER_STATUS_MANAGER);
        }
        break;
      case OrderDto.ORDER_STATUS_ENGINER:
        if (order.getElectricalFee() == null 
        || order.getInstallFee() == null 
//        || order.getMaintenanceFee() == null 
        || order.getMaterialFee() == null ) {
//        || order.getRefrigeratoryFee() == null ) {
          throw new RuntimeException("安装费、材料费、电器费不能为空");
        }
        order.setStatus(OrderDto.ORDER_STATUS_MANAGER);
        break;
      default:
        throw new RuntimeException("未知订单状态【" + status + "】");
    }

    status = order.getStatus();
    orderInfoMapper.updateStatus(order.getId(), user, status, new Date(), null, null, null);

    // send mail to notification
    try {
      String to = "";
      switch (status) {
        case OrderDto.ORDER_STATUS_B2C:
          to = getRoleUserMails(Constant.ROLE_B2C);
          break;
        case OrderDto.ORDER_STATUS_ENGINER:
          to = getRoleUserMails(Constant.ROLE_ENGINEER);
          break;
        case OrderDto.ORDER_STATUS_MANAGER:
          to = getRoleUserMails(Constant.ROLE_MANAGER);
          break;
        case OrderDto.ORDER_STATUS_DRAFT:
        case OrderDto.ORDER_STATUS_REJECT:
        default:
          logger.error("未知订单状态，发送邮件失败");
          return;
      }
      if (to.length() == 0) {
        logger.error("发送邮件失败！没有找到对应的用户");
        return;
      }
      sendApproveNoticeMail(order, to);
    } catch (Exception e) {
      logger.error("发送邮件失败", e);
    }
  }

  /**
   * 发送订单审批通知邮件
   * @param order
   * @param to
   */
  private void sendApproveNoticeMail(OrderDto order, String to) {
    Mail mail = new Mail();
//    mail.setId(UUID.randomUUID().toString());
    mail.setFrom(null);
    mail.setTo(to);
    Map<String, Object> valMap = new ObjectMapper().convertValue(order, Map.class);
    String body = mailService.render("/order-" + order.getStOrderType() + "-notice.html", "HTML5", valMap);
    mail.setBody(body);
    String subject = mailService.render("/order-" + order.getStOrderType() + "-subject.html", "HTML5", valMap);
    mail.setSubject(subject);

    logger.info("order mail: \n" + mail);
    boolean sendStatus = mailService.send(mail);
    
    if (!sendStatus) {
      logger.error("发送邮件失败！");
    }
  }

  /**
   * 驳回订单，逐级驳回
   * 
   * @param order
   * @throws Exception
   */
  @Transactional
  public void reject(String user, Integer orderInfoId) throws Exception {
    OrderDto order = this.findOrder(orderInfoId);
    if (order == null) {
      throw new RuntimeException("订单不存在，id=" + orderInfoId);
    }
    String status = order.getStatus();
    String stOrderType = order.getStOrderType();
    String rejectStatus = "";
    switch (status) {
      case OrderDto.ORDER_STATUS_B2C:
        rejectStatus = OrderDto.ORDER_STATUS_REJECT;
        break;
      case OrderDto.ORDER_STATUS_ENGINER:
        if (order.getIsB2c() == 1) {
          rejectStatus = OrderDto.ORDER_STATUS_B2C;
        } else {
          rejectStatus = OrderDto.ORDER_STATUS_REJECT;
        }
        break;
      case OrderDto.ORDER_STATUS_MANAGER:
        if (stOrderType.equals("3") || stOrderType.equals("4")) {
          // 直销客户订单和直销客户报价单
          rejectStatus = OrderDto.ORDER_STATUS_ENGINER;
        } else if (order.getIsB2c() == 1) {
          rejectStatus = OrderDto.ORDER_STATUS_B2C;
        } else {
          rejectStatus = OrderDto.ORDER_STATUS_REJECT;
        }
        break;
      default:
        throw new RuntimeException("订单当前状态【" + status + "】不能驳回");
    }
    orderInfoMapper.updateStatus(orderInfoId, user, rejectStatus, null, null, null, null);

    // send mail to notification
    try {
      String to = "";
      switch (rejectStatus) {
        case OrderDto.ORDER_STATUS_B2C:
          to = getRoleUserMails(Constant.ROLE_B2C);
          break;
        case OrderDto.ORDER_STATUS_ENGINER:
          to = getRoleUserMails(Constant.ROLE_ENGINEER);
          break;
        case OrderDto.ORDER_STATUS_MANAGER:
          to = getRoleUserMails(Constant.ROLE_MANAGER);
          break;
        case OrderDto.ORDER_STATUS_DRAFT:
        case OrderDto.ORDER_STATUS_REJECT:
          to = userMapper.findByLoginName(order.getSalesCode()).getUserMail();
          String manager = order.getContractManager();
          if (StringUtils.isNotEmpty(manager)) {
            to += ";" + userMapper.findByLoginName(manager).getUserMail();
          }
          break;
        default:
          logger.error("未知订单状态，发送邮件失败");
          return;
      }
      if (to.length() == 0) {
        logger.error("发送邮件失败！没有找到对应的用户");
        return;
      }
      sendApproveNoticeMail(order, to);
    } catch (Exception e) {
      logger.error("发送邮件失败", e);
    }
  }

  /**
   * 返回角色包含的所有用户的邮箱地址
   * @param toRoleId
   * @return
   */
  public String getRoleUserMails(Integer toRoleId) {
    Map<String, Object> params = new HashMap<>();
    params.put("roleId", toRoleId);
    params.put("isActive", 1);
    List<User> users = userMapper.findByParams(params);
    StringBuilder to = new StringBuilder(256);
    for (User u : users) {
      String name = u.getName();
      String mail = u.getUserMail();
//      to.append(";").append("<").append(name).append(">").append(mail);
      to.append(";").append(mail);
    }
    if (to.length() > 0) {
      return to.substring(1);
    } else {
      return "";
    }
  }

  /**
   * 
   * @return sales type
   */
  public List<SalesType> getSalesTypes() {
    return salesTypeRepo.findAll();
  }


  /**
   * 
   * @return
   */
  public OrderOption getOrderOption() {
    OrderOption oo = new OrderOption();
    //
    List<Province> bps = provinceRepo.findAll();
    Map<String, String> provinces = oo.getProvinces();
    for (Province bp : bps) {
      provinces.put(bp.getCode(), bp.getName());
    }
    //
    List<City> bcs = cityRepo.findAll();
    Map<String, Map<String, String>> citys = oo.getCitys();
    for (City bc : bcs) {
      String pcode = bc.getProvinceCode();
      Map<String, String> vals = new HashMap<String, String>();
      vals.put(bc.getCode(), bc.getName());
      if (citys.putIfAbsent(pcode, vals) != null) {
        citys.get(pcode).put(bc.getCode(), bc.getName());
      }

    }
    //
    List<Area> bas = districtRepo.findAll();
    Set<Area> distincts = oo.getDistricts();
    distincts.addAll(bas);
    //
    Map<String, String> incs = constService.findIndustryCodes();
    oo.setTermialClass(incs);
    //
    Map<String, String> st = oo.getSaleTypes();
    List<SalesType> dsts = saleTypeRepo.findAll();
    for (SalesType dst : dsts) {
      st.put(dst.getCode(), dst.getName());
    }
    //
    // Map<String, Map<String, String>> offices = oo.getOffices();
    // List<SapSalesOffice> ssos = officeRepo.findAll();
    // for (SapSalesOffice so : ssos) {
    // String tcode = so.getTypeCode();
    // Map<String, String> vals = new HashMap<String, String>();
    // vals.put(so.getCode(), so.getName());
    // if (offices.putIfAbsent(tcode, vals) != null) {
    // offices.get(tcode).put(so.getCode(), so.getName());
    // }
    // }
    // //
    // Map<String, Map<String, String>> groups = oo.getGroups();
    // List<SapSalesGroup> ssgs = groupsRepo.findAll();
    // for (SapSalesGroup ssg : ssgs) {
    // String ocode = ssg.getOfficeCode();
    // Map<String, String> vals = new HashMap<String, String>();
    // vals.put(ssg.getCode(), ssg.getName());
    // if (groups.putIfAbsent(ocode, vals) != null) {
    // groups.get(ocode).put(ssg.getCode(), ssg.getName());
    // }
    // }
    //
    Map<String, String> payments = oo.getPaymentType();
    Map<String, String> bidding = oo.getBiddingPlan();
    List<PaymentTerm> pts = paymentRepo.findAll();
    for (PaymentTerm pt : pts) {
      if (pt.getIsPaymentTerm()) {
        payments.put(pt.getCode(), pt.getName());
      } else {
        bidding.put(pt.getCode(), pt.getName());
      }
    }
    //
    oo.setOrderTypes(constService.getOrderTypes());

    // 终端客户性质选择 Industry Codes For dealer
    oo.setDealerIndustryCodes(constService.findFordealerIndustryCodes());

    // 物料评估类MaterialGroups
    oo.setMaterialGroups(constService.findMaterialGroups());

    // 物料在订单上的分类MaterialGroupOrders
    oo.setMaterialGroupOrders(constService.findMaterialGroupOrders());

    // 物料评估类 与 物料在订单上的分类映射
    oo.setMaterialGroupMapGroupOrder(constService.findMaterialGroupMapGroupOrders());

    // 销售区域Sales Offices
    oo.setOffices(constService.findSalesOffices());

    // 销售区域Sales Goups
    oo.setGroups(constService.findSalesGroups());

    // 运输方式ShippingTypes
    oo.setShippingTypes(constService.findShippingTypes());

    // 收货方式ReceiveTerms
    oo.setReceiveTerms(constService.findReceiveTerms());

    // 国际贸易条款
    oo.setIntercoms(constService.findIncoterms());

    // 币种
    oo.setExchangeRate(constService.findCurrencies());

    // 安装方式
    oo.setInstallationTerms(constService.findInstallationTerms());

    // 标准折扣，Code：std_discount
    Settings p = settingsRepository.findEnabledInfo("std_discount");
    if (p != null) {
      oo.setStandardDiscount(p.getsValue());
    }

    // 税率，Code：tax_rate
//    p = settingsRepository.findEnabledInfo("tax_rate");
//    if (p != null) {
//      Map<String, Double> taxRate = new HashMap<String, Double>();
//      taxRate.put("10", Double.valueOf(p.getsValue()));
//      taxRate.put("30", Double.valueOf(p.getsValue()));
//
//      oo.setTaxRate(taxRate);
//    }
    List<Tax> taxs = taxMapper.findByParams(null);
    Map<String, List<Map<String, Object>>> taxRate = new HashMap<String, List<Map<String, Object>>>();
    oo.setTaxRate(taxRate);
    taxRate.put("10", new ArrayList<Map<String, Object>>()); // 内销
    taxRate.put("20", new ArrayList<Map<String, Object>>()); // 出口
    taxRate.put("30", new ArrayList<Map<String, Object>>()); // 冷库
    for (Tax tax : taxs) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", tax.getCode());
        map.put("tax", tax.getTax());
        if (tax.getCode().equals("0")) {
            taxRate.get("20").add(map);
        } else {
            taxRate.get("10").add(map);
            taxRate.get("30").add(map);
        }
    }

    // 税率，Code：tax_rate
    List<Settings> settings = settingsService.findDistinctInfo();
    Map<String, String> settingsMap = new HashMap<String, String>();
    oo.setSettings(settingsMap);
    for (Settings s : settings) {
      settingsMap.put(s.getCode(), s.getsValue());
    }

    // 经销商结算方式
    oo.setDealerPaymentTerms(constService.findDealerPaymentTerms());

    return oo;
  }

  /**
   * 根据流水号组装数据并同步SAP
   * 
   * @param sequenceNumber
   * @param version
   * @return
   * @throws Exception
   */
  @Transactional
  public String sendToSap(String user, Integer orderInfoId) throws Exception {
    OrderDto orderDto = this.findOrder(orderInfoId);
    // this.save(user, orderDto);
    String stOrderType = orderDto.getStOrderType();
    String status = orderDto.getStatus();
    
    if (StringUtils.trimToEmpty(orderDto.getContractNumber()).length() == 0) {
      throw new RuntimeException("合同号不能为空");
    }

    if ("3".equals(stOrderType)) {
      throw new RuntimeException("直销客户投标报价单不能下发SAP");
    }

    if (!OrderDto.ORDER_STATUS_APPROVED.equals(status)
        && !OrderDto.ORDER_STATUS_APPROVED_UPDATE.equals(status)) {
      throw new RuntimeException("只有BPM审批通过才能下发SAP");
    }
/*
    // 经销商订单要BPM审批通过并且上上签双方已签署才可以下推sap，不校验“出口”订单
    if (("1".equals(stOrderType) || "2".equals(stOrderType)) && !orderDto.getSaleType().equals("20")) {
    	Map<String, Object> params = new HashMap<>();
    	params.put("orderInfoId", orderInfoId);
    	List<ContractDto> contracts = contractService.find(params).getRows();
    	if (contracts == null || contracts.size() == 0 || !contracts.get(0).getStatus().equals("06")) {
    		throw new RuntimeException("经销商订单要上上签双方已签署才可以下发SAP");
    	}
    }
*/
    if (orderDto.isHasSendSap()) {
      sapOrderService.updateOrder(orderDto);
      // 修改订单状态为已下发SAP
      orderInfoMapper.updateStatus(orderDto.getId(), user, OrderDto.ORDER_STATUS_SAP, null, null,
          null, null);
//      orderInfoMapper.updateStatus(orderDto.getId(), user, OrderDto.ORDER_STATUS_SAP_UPGRADE_TEMP, null, null,
//          null, null);
    } else {
      sapOrderService.createOrder(orderDto);
      // 修改订单状态为已下发SAP
      orderInfoMapper.updateStatus(orderDto.getId(), user, OrderDto.ORDER_STATUS_SAP, null, null,
          null, null);
//      orderInfoMapper.updateStatus(orderDto.getId(), user, OrderDto.ORDER_STATUS_SAP_TEMP, null, null,
//          null, null);
    }
    // 修改行项目状体为已下发SAP
    // 异步推送sap后移到检查订单执行成功再修改
//    updateItemToSapStatus(orderInfoId);

    return "success";

  }

  /**
   * 下推sap成功后修改行项目状态为10（已下推sap）
   * 
   * @param orderInfoId
   */
	public void updateItemToSapStatus(Integer orderInfoId) {
		Item item = new Item();
		item.setOrderInfoId(orderInfoId);
		item.setItemStatus("10");
		itemMapper.updateSendSapStatusByOrderInfo(item);
	}

  /**
   * 查询订单版本历史
   * 
   * @param sequenceNubmer
   * @return
   */
  public List<OrderVersion> findOrderVersions(String sequenceNumber) {
    List<OrderVersion> versions = orderInfoMapper.findOrderVersions(sequenceNumber);

    return versions;
  }

  /**
   * 查询订单详情
   * 
   * @param query
   * @return
   * @throws Exception
   */
  public OrderDto findOrder(Integer id) throws Exception {
    OrderDto order = null;
    OrderQuery orderQuery = new OrderQuery();
    orderQuery.setId(id);
    orderQuery.setIncludeDetail(true);

    PageInfo<OrderDto> page = queryOrderView(orderQuery);
    List<OrderDto> orderViews = page.getList();

    if (orderViews.size() > 0) {
      order = orderViews.get(0);
    }

    return order;
  }

  /**
   * 查询订单详情
   * 
   * @param query
   * @return
   * @throws Exception
   */
//  public OrderDto findOrder(String sequenceNumber, String version) throws Exception {
//    OrderDto order = null;
//    OrderQuery orderQuery = new OrderQuery();
//    orderQuery.setSequenceNumber(sequenceNumber);
//    orderQuery.setVersion(version);
//    orderQuery.setIncludeDetail(true);
//
//    PageInfo<OrderDto> page = queryOrderView(orderQuery);
//    List<OrderDto> orderViews = page.getList();
//
//    if (orderViews.size() > 0) {
//      order = orderViews.get(0);
//    }
//
//    return order;
//  }

  /**
   * 查询订单
   * 
   * @param query
   * @return
   * @throws Exception
   */
  public PageInfo<OrderDto> findOrders(OrderQuery orderQuery) throws Exception {
    // boolean includeDetail = orderQuery.isIncludeDetail();

    PageInfo<OrderDto> page = queryOrderView(orderQuery);

    return page;
  }

  /**
   * 组装订单详情
   * 
   * @param order
   * @param orderId
   * @param orderInfoId
   * @throws InvocationTargetException
   * @throws IllegalAccessException
   */
  private void assembleOrderDetail(OrderDto order) throws Exception {
    Integer orderInfoId = order.getId();
    // Attached File
    order.setAttachments(new ArrayList<Attachment>());
    List<Attachment> attachments = attachmentMapper.findByOrderInfoId(orderInfoId);
    order.setAttachments(attachments);

    // 收货地址
    List<DeliveryAddressDto> addresses = deliveryAddressMapper.findByOrderInfoId(orderInfoId);
    order.setDeliveryAddress(addresses);

    // billing plan
    List<BillingPlan> billingPlanList = billingPlanMapper.findByOrderInfoId(orderInfoId);
    order.setPayments(billingPlanList);

    // items
    assembleItems(order);
  }

  private void assembleItems(OrderDto order) throws Exception {
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("orderInfoId", order.getId());
    List<ItemDto> items = itemMapper.findByParams(params);
    order.setItems(items);
    for (ItemDto item : items) {
      Integer itemId = item.getId();
      
      if (item.getItemStatus().equals("10") || item.getItemStatus().equals("Z2")) {
        order.setHasSendSap(true);
      }

      params = new HashMap<>();
      params.put("code", item.getMaterialCode());
      MaterialDto m = sapViewMapper.findMaterialInfo(params).get(0);

      item.setMaterialName(m.getDescription());
      item.setMaterialGroupCode(m.getGroupCode());
      item.setMaterialGroupName(m.getGroupName());
      item.setMaterialType(m.getMaterialType());
      item.setStMaterialGroupCode(m.getStGroupCode());
      item.setStMaterialGroupName(m.getStGroupName());
      item.setUnitCode(m.getUnitCode());
      item.setUnitName(m.getUnitName());
      item.setIsConfigurable(m.isConfigurable());
      item.setIsPurchased(m.isPurchased());
      item.setVolumeCube(m.getMaterialSize());

      Integer deliveryAddressSeq = item.getDeliveryAddressSeq();
      if (deliveryAddressSeq != null) {
        order.getDeliveryAddress().forEach(e -> {
          if (e.getSeq() != null && e.getSeq().equals(deliveryAddressSeq)) {
            item.setProvinceCode(e.getProvinceCode());
            item.setProvinceName(e.getProvinceName());
            item.setCityCode(e.getCityCode());
            item.setCityName(e.getCityName());
            item.setDistrictCode(e.getDistrictCode());
            item.setDistrictName(e.getDistrictName());
          }
        });
      }

      List<CharacteristicDto> configs = new ArrayList<>();
      item.setConfigs(configs);

      // colors
      List<CharacteristicDto> colors = itemColorMapper.findByItemId(itemId);
      colors.forEach(e -> {
          // UNKNOWN为同步物料特征表出现的新特征
        if (StringUtils.trimToEmpty(e.getValueCode()).equals("") || StringUtils.trimToEmpty(e.getValueCode()).equals("UNKNOWN")) {
          e.setValueCode("-");
        }
      });
      configs.addAll(colors);
      // characteristics
      List<CharacteristicDto> characteristics = characteristicsMapper.findByItemId(itemId);
      // 将特征值为空的转换为 - 号
      characteristics.forEach(e -> {
          // UNKNOWN为同步物料特征表出现的新特征
        if (StringUtils.trimToEmpty(e.getValueCode()).equals("") || StringUtils.trimToEmpty(e.getValueCode()).equals("UNKNOWN")) {
          e.setValueCode("-");
        }
      });
      configs.addAll(characteristics);

      // item attachment
      List<ItemAttachment> attachments = itemAttachmentMapper.findByItemId(itemId);
      item.setAttachments(attachments);
    }
  }

  /**
   * 根据条件查询订单视图
   * 
   * @param orderQuery
   * @return
   * @throws Exception
   */
  private PageInfo<OrderDto> queryOrderView(OrderQuery orderQuery) throws Exception {
    boolean includeDetail = orderQuery.isIncludeDetail();
    // 设置分页信息
    int pageNo = orderQuery.getPageNo() == null ? 0 : orderQuery.getPageNo().intValue();
    int pageSize = orderQuery.getPageSize() == null ? 10000 : orderQuery.getPageSize();

    com.github.pagehelper.PageHelper.startPage(pageNo, pageSize);
    List<OrderDto> orders = orderInfoMapper.findOrderViewByParams(orderQuery);
    for (OrderDto order : orders) {
      // user name
      List<User> list = userService.findByIdentitys(order.getCreater(), order.getUpdater(),
          order.getSalesCode(), order.getContractManager());
      list.forEach(u -> {
        if (u.getUserIdentity().equals(order.getCreater())) {
          order.setCreaterName(u.getName());
        }
        if (u.getUserIdentity().equals(order.getUpdater())) {
          order.setUpdaterName(u.getName());
        }
        if (u.getUserIdentity().equals(order.getSalesCode())) {
          order.setSalesName(u.getName());
          order.setUserOfficeCode(u.getOfficeCode());
        }
        // if (u.getUserIdentity().equals(order.getContractManager())) {
        // order.setContractManager(u.getName());
        // }
      });

      // customerclazz
      order.setCustomerClazzName(constService.findCustomerClazzByCode(order.getCustomerClazz()));

      Map<String, Object> params = new HashMap<String, Object>();
      params.put("code", order.getCustomerCode());
      List<CustomerDto> customers = sapViewMapper.findCustomer(params);
      CustomerDto customer = customers.get(0);
      // customer class name
      order.setCustomerClazzName(customer.getClazzName());
      // customer industry code
      order.setCustomerIndustryCode(customer.getIndustryCodeCode());
      order.setCustomerIndustryCodeName(customer.getIndustryCodeName());
      // customer industry
      order.setCustomerIndustry(customer.getIndustryCode());
      order.setCustomerIndustryName(customer.getIndustryName());

      order.setTransferTypeName(constService.findShippingTypes().get(order.getTransferType()));

      if (includeDetail) {
        assembleOrderDetail(order);
      }
    }
    
    for (OrderDto orderDto : orders) {
      // 转换折扣数据存储格式，0.480 -> 48.0
      convertDiscountToInteger(orderDto);
    }

    return new PageInfo<>(orders);
  }

  /**
   * 转换折扣数据存储格式，0.480 -> 48.0
   * @param orderDto
   */
  private void convertDiscountToInteger(OrderDto orderDto) {
    orderDto.setApprovedBodyDiscount(orderDto.getApprovedBodyDiscount() * 100);
    orderDto.setApprovedMainDiscount(orderDto.getApprovedMainDiscount() * 100);
    orderDto.setBodyDiscount(orderDto.getBodyDiscount() * 100);
    orderDto.setMainDiscount(orderDto.getMainDiscount() * 100);
    orderDto.setDiscount(orderDto.getDiscount() * 100);
    List<ItemDto> items = orderDto.getItems();
    if (items != null && items.size() > 0) {
      items.forEach(e -> {
        e.setDiscount(e.getDiscount() * 100);
      });
    }
  }

  /**
   * 
   * 发送订单到BPM审批
   * 
   * @param order
   * @throws Exception
   */
  @Transactional
  public void submitBpm(String user, OrderDto order) throws Exception {
    if (StringUtils.trimToEmpty(order.getContractNumber()).length() == 0) {
      // 经销商非标准折扣下单、直签报价单、直签订单提交BPM时不校验合同号
      if (!order.getStOrderType().equals("2") && !order.getStOrderType().equals("3") && !order.getStOrderType().equals("4")) {
        throw new RuntimeException("合同号不能为空");
      }
    }
    if (!order.getStatus().equals(OrderDto.ORDER_STATUS_MANAGER)) {
      throw new RuntimeException("订单当前状态不能提交BPM");
    }
    order = save(user, order);
    List<ItemDto> items = order.getItems();
    if (items == null) {
      items = new ArrayList<ItemDto>();
    }
    
    // 冷库处理
    double refrigeratoryFee = ObjectUtils.defaultIfNull(order.getRefrigeratoryFee(), 0d);
    boolean hasRefrigeratoryItem = false;
    for (ItemDto item : items) {
      if (item.getMaterialCode().equals("BG1R8R00000-X")) {
        hasRefrigeratoryItem = true;
        break;
      }
    }
    if (hasRefrigeratoryItem && refrigeratoryFee <= 0) {
      throw new RuntimeException("有冷库行项目，冷库费用必须有值");
    }
    if (!hasRefrigeratoryItem && refrigeratoryFee > 0) {
      throw new RuntimeException("有冷库费用，没有冷库行项目");
    }
    
    sendToBpm(user, order);
    // 修改订单状态
    orderInfoMapper.updateStatus(order.getId(), user, OrderDto.ORDER_STATUS_BPM, null, new Date(),
        null, null);
  }

  /**
   * 提交订单到BPM
   * @param user
   * @param order
   * @throws JsonProcessingException
   * @throws JsonMappingException
   */
  public void sendToBpm(String user, OrderDto order)
      throws JsonProcessingException, JsonMappingException {
    List<ItemDto> items = order.getItems();
    List<Attachment> attachments = order.getAttachments();
    if (attachments == null) {
      attachments = new ArrayList<Attachment>();
    }
    List<MaterialGroups> grossProfitMargins = new ObjectMapper()
        .readValue(order.getGrossProfitMargin(), new TypeReference<List<MaterialGroups>>() {});
    MaterialGroups sumMargin = grossProfitMargins.get(grossProfitMargins.size() - 1);

    BpmOrder bpmOrder = new BpmOrder();
    OrderHeader bpmHeader = new OrderHeader();
    List<OrderAttachment> bpmAttachments = new ArrayList<OrderAttachment>(attachments.size());
    List<OrderItem> bpmItems = new ArrayList<OrderItem>(items.size());
    List<OrderMargin> bpmMargins = new ArrayList<OrderMargin>(grossProfitMargins.size());
    List<OrderMargin> bpmWtwMargins = new ArrayList<OrderMargin>(grossProfitMargins.size());

    bpmOrder.setOrder(bpmHeader);
    bpmOrder.setAttachments(bpmAttachments);
    bpmOrder.setItems(bpmItems);
    bpmOrder.setMargin(bpmMargins);
    bpmOrder.setWtwMargin(bpmWtwMargins);

    // set bpm order
    List<DeliveryAddressDto> addresses = order.getDeliveryAddress();
    if (addresses != null && addresses.size() > 0) {
      StringBuilder strAddress = new StringBuilder(512);
      for (DeliveryAddressDto deliveryAddressDto : addresses) {
        String provinceName = StringUtils.isEmpty(deliveryAddressDto.getProvinceCode()) ? ""
            : provinceRepo.findById(deliveryAddressDto.getProvinceCode()).get().getName();
        String cityName = StringUtils.isEmpty(deliveryAddressDto.getCityCode()) ? ""
            : cityRepo.findById(deliveryAddressDto.getCityCode()).get().getName();
        String districtName = StringUtils.isEmpty(deliveryAddressDto.getDistrictCode()) ? ""
            : districtRepo.findById(deliveryAddressDto.getDistrictCode()).get().getName();
        String address = StringUtils.trimToEmpty(deliveryAddressDto.getAddress());
        strAddress.append(",").append(provinceName).append(cityName).append(districtName)
            .append(address);
      }
      bpmHeader.setAddress(strAddress.substring(1));
    } else {
      bpmHeader.setAddress("");
    }
    bpmHeader.setApprovalDiscount(order.getDiscount());
    bpmHeader.setB2c(String.valueOf(order.getIsB2c()));
    bpmHeader.setBodyDiscount(order.getBodyDiscount());
    bpmHeader.setComments(StringUtils.trimToEmpty(order.getComments()));
    bpmHeader.setContractAmount(order.getContractValue());
    bpmHeader.setContractNumber(StringUtils.trimToEmpty(order.getContractNumber()));
    bpmHeader.setContractRmbAmount(order.getContractRmbValue());
    bpmHeader.setCreateTime(order.getCreateTime());
    String currencyName = constService.findAllCurrency().get(order.getCurrency()).getName();
    bpmHeader.setCurrencyName(StringUtils.trimToEmpty(currencyName));
    bpmHeader.setCustomerName(StringUtils.trimToEmpty(order.getCustomerName()));
    bpmHeader.setDealer(order.getOrderType().equals(OrderDto.ORDER_TYPE_DEALER) ? "1" : "0");
    bpmHeader.setDiscount(order.getDiscount());
    bpmHeader.setEarliestDeliveryDate(order.getEarliestDeliveryDate());
    bpmHeader.setElectricalFee(ObjectUtils.defaultIfNull(order.getElectricalFee(), 0d));
    bpmHeader.setExchange(ObjectUtils.defaultIfNull(order.getCurrencyExchange(), 0d));
    bpmHeader.setInstallFee(ObjectUtils.defaultIfNull(order.getInstallFee(), 0d));
    bpmHeader.setMaintenanceFee(ObjectUtils.defaultIfNull(order.getMaintenanceFee(), 0d));
    bpmHeader.setMargin(ObjectUtils.defaultIfNull(sumMargin.getGrossProfitMargin(), 0d));
    bpmHeader.setMaterialFee(ObjectUtils.defaultIfNull(order.getMaterialFee(), 0d));

    bpmHeader.setMergeDiscount(ObjectUtils.defaultIfNull(bpmHeader.getDiscount(), 0d));
    bpmHeader.setOrderType(StringUtils.trimToEmpty(order.getOrderType()));
//    1 经销商标准折扣下单
//    2 经销商非标准折扣下单
//    3 直签客户投标报价
//    4 直签客户下定单
//    5 备货 
    String stOrderType = order.getStOrderType();
    String stOrderTypeDesc = "";
    switch (stOrderType) {
      case "1" :
        stOrderTypeDesc = "经销商标准折扣下单";
        break;
      case "2" :
        stOrderTypeDesc = "经销商非标准折扣下单";
        break;
      case "3" :
        stOrderTypeDesc = "直签客户投标报价";
        break;
      case "4" :
        stOrderTypeDesc = "直签客户下定单";
        break;
      case "5" :
        stOrderTypeDesc = "备货";
        break;
    }
    bpmHeader.setStOrderType(stOrderTypeDesc);
    // 结算方式
    String paymentType = StringUtils.trimToEmpty(order.getPaymentType());
    String paymentTypeName = constService.findDealerPaymentTerms().get(paymentType);
    if (StringUtils.isEmpty(paymentTypeName)) {
      paymentTypeName = paymentType;
    }
    bpmHeader.setPaymentTypeName(paymentTypeName);
    // 运输类型
    String receiveTypeName = constService.findShippingTypes().get(order.getTransferType());
    bpmHeader.setReceiveTypeName(StringUtils.trimToEmpty(receiveTypeName));
    bpmHeader.setRefrigeratoryFee(ObjectUtils.defaultIfNull(order.getRefrigeratoryFee(), 0d));
    bpmHeader.setSalesCode(StringUtils.trimToEmpty(order.getSalesCode()));
    bpmHeader.setSalesTel(StringUtils.trimToEmpty(order.getSalesTel()));
    bpmHeader.setSaleType(StringUtils.trimToEmpty(order.getSaleType()));
    bpmHeader.setSapOffice(StringUtils.trimToEmpty(order.getOfficeName()));
    bpmHeader.setSequenceNumber(StringUtils.trimToEmpty(order.getSequenceNumber()));
    bpmHeader.setShopName(StringUtils.trimToEmpty(order.getShopName()));
    bpmHeader.setSpecialDiscount(order.getStOrderType().equals("2") ? "1" : "0");
    // 非统一折扣
    bpmHeader.setIsNonUniform(order.getIsSpecial());
    bpmHeader.setStatus(StringUtils.trimToEmpty(order.getStatus()));
    bpmHeader.setTaxRate(ObjectUtils.defaultIfNull(order.getTaxRate(), 0d));
    bpmHeader.setUnitDiscount(ObjectUtils.defaultIfNull(order.getMainDiscount(), 0d));
    bpmHeader.setWtwMargin(ObjectUtils.defaultIfNull(sumMargin.getWtwGrossProfitMargin(), 0d));
    bpmHeader.setIsUrgentDelivery(ObjectUtils.defaultIfNull(order.getIsUrgentDelivery(), 0));
    bpmHeader.setIsSpecialOrder(order.getIsSpecialOrder()); // 特批下单
    bpmHeader.setIsLongterm(order.getIsLongterm()); // 长期折扣
    bpmHeader.setIsSpecial(order.getStOrderType().equals("2") ? 1 : 0); // 经销商非标折扣
    bpmHeader.setIsNonUniform(order.getIsSpecial()); //  经销商非标折扣订单非统一折扣
    
    // 特批发货申请信息
    SpecialOrderApplication specialOrderApplication =
            specialOrderApplicationMapper.findByOrderInfo(order.getId());
    if (specialOrderApplication != null && specialOrderApplication.getApplyStatus().equals(1)) {
        bpmHeader.setReceiveMailTime(specialOrderApplication.getReceiveMailTime());
        bpmHeader.setContractTime(specialOrderApplication.getContractTime());
        bpmHeader.setPayAdvancePaymentTime(specialOrderApplication.getPayAdvancePaymentTime());
        bpmHeader.setRemark(specialOrderApplication.getRemark());
    }
    
    // 变更信息
    bpmHeader.setIsUpgrade(order.getVersionNum() > 1 ? 1 : 0);
    bpmHeader.setIsSendToSap(order.isHasSendSap() ? 1 : 0);
		if (order.getVersionNum() > 1) {
			OrderInfo lastVersionOrder = orderInfoMapper.findLastVersion(order.getId());
			List<MaterialGroups> lastGrossProfitMargins = new ObjectMapper()
					.readValue(lastVersionOrder.getGrossProfitMargin(),
							new TypeReference<List<MaterialGroups>>() {
							});
			MaterialGroups lastSumMargin = lastGrossProfitMargins
					.get(lastGrossProfitMargins.size() - 1);
			double marginDelta = lastSumMargin.getGrossProfitMargin()
					- sumMargin.getGrossProfitMargin();
			bpmHeader.setMarginDelta(marginDelta);
		}

    // set bpm order attachements
    for (Attachment attachment : attachments) {
      OrderAttachment orderAttachment = new OrderAttachment();
      bpmAttachments.add(orderAttachment);

      orderAttachment.setName(attachment.getFileName());
      orderAttachment.setUrl(attachment.getFileUrl());
    }

    // set bpm order item
    if (items.size() > 0) {
      StringBuilder strGroupName = new StringBuilder(256);
      double exchange = bpmHeader.getExchange();
      for (ItemDto itemDto : items) {
        strGroupName.append(",").append(itemDto.getMaterialGroupName());
        OrderItem bpmItem = new OrderItem();
        bpmItems.add(bpmItem);

        bpmItem.setActuralAmount(itemDto.getActualPrice() * itemDto.getQuantity() / exchange); // 凭证货币
        bpmItem
            .setActuralAmountOfOptional(itemDto.getOptionalActualPrice() * itemDto.getQuantity() / exchange); // 凭证货币
        bpmItem.setActuralPrice(itemDto.getActualPrice() / exchange); // 凭证货币
        bpmItem.setActuralPriceOfOptional(itemDto.getOptionalActualPrice() / exchange); // 凭证货币
        bpmItem.setAddress(StringUtils.trimToEmpty(itemDto.getAddress()));
        bpmItem.setB2cAmountEstimated(itemDto.getB2cEstimatedPrice() * itemDto.getQuantity());
        bpmItem.setB2cComments(StringUtils.trimToEmpty(itemDto.getB2cComments()));
        bpmItem.setB2cCostOfEstimated(itemDto.getB2cEstimatedCost() * itemDto.getQuantity());
        bpmItem.setB2cPriceEstimated(itemDto.getB2cEstimatedPrice());
        bpmItem.setColorComments(StringUtils.trimToEmpty(itemDto.getColorComments()));
        bpmItem.setDeliveryDate(itemDto.getDeliveryDate());
        bpmItem.setDiscount(itemDto.getDiscount());
        bpmItem.setItemCategoryName(StringUtils.trimToEmpty(itemDto.getItemCategory()));
        bpmItem
            .setItemRequirementPlanName(StringUtils.trimToEmpty(itemDto.getItemRequirementPlan()));
        bpmItem.setMaterialCode(StringUtils.trimToEmpty(itemDto.getMaterialCode()));
        bpmItem.setMaterialAttribute(itemDto.getIsPurchased() ? "生产" : "采购");
        bpmItem.setMaterialGroup(itemDto.getMaterialGroupCode());
        bpmItem.setMaterialGroupName(StringUtils.trimToEmpty(itemDto.getMaterialGroupName()));
        bpmItem.setMaterialName(StringUtils.trimToEmpty(itemDto.getMaterialName()));
        String unitName = this.constService.findMeasurementUnits().get(itemDto.getUnitCode());
        bpmItem.setMeasureUnitName(StringUtils.trimToEmpty(unitName));
        bpmItem.setOnStoreDate(itemDto.getOnStoreDate());
        bpmItem.setPeriod(ObjectUtils.defaultIfNull(itemDto.getPeriod(), 0));
        bpmItem.setProduceDate(itemDto.getProduceDate());
        bpmItem.setQuantity(itemDto.getQuantity());
        bpmItem.setTotalSellingPrice(itemDto.getActualPrice() + itemDto.getOptionalActualPrice() + itemDto.getB2cEstimatedPrice());
        bpmItem.setTotalSellingAmount(bpmItem.getTotalSellingPrice() * itemDto.getQuantity());
        bpmItem.setTotalTransferAmount((itemDto.getTransactionPrice() + itemDto.getOptionalTransactionPrice() + itemDto.getB2cEstimatedCost()) * itemDto.getQuantity());
        bpmItem.setRetailAmount(itemDto.getRetailPrice() * itemDto.getQuantity());
        bpmItem.setRetailPrice(itemDto.getRetailPrice());
        bpmItem.setRowNumber(itemDto.getRowNum());
        bpmItem.setShippDate(itemDto.getShippDate());
        bpmItem.setSpecialComments(StringUtils.trimToEmpty(itemDto.getSpecialComments()));
        bpmItem.setStandardPriceOfOptional(ObjectUtils.defaultIfNull(itemDto.getOptionalStandardPrice(), 0D));
        bpmItem.setTransactionPriceOfOptional(itemDto.getOptionalTransactionPrice() / exchange); // 凭证货币
        bpmItem.setTransfterPrice(itemDto.getTransactionPrice() / exchange); // 凭证货币
        double standardCost = itemDto.getStandardPrice()
            + (itemDto.getOptionalStandardPrice() == null ? 0 : itemDto.getOptionalStandardPrice());
        bpmItem.setStandardCost(standardCost / exchange); // 凭证货币
      }
      bpmHeader.setMaterialGroupNames(strGroupName.length() > 0 ? strGroupName.substring(1) : "");
    }

    // set bpm order margins and wtw margins
    for (MaterialGroups grossProfitMargin : grossProfitMargins) {
      OrderMargin margin = new OrderMargin();
      OrderMargin wtwMargin = new OrderMargin();

      bpmMargins.add(margin);
      bpmWtwMargins.add(wtwMargin);

      margin.setAmount(ObjectUtils.defaultIfNull(grossProfitMargin.getAmount(), BigDecimal.ZERO));
      margin.setCode(StringUtils.trimToEmpty(grossProfitMargin.getCode()));
      margin.setName(StringUtils.trimToEmpty(grossProfitMargin.getName()));
      margin.setCost(ObjectUtils.defaultIfNull(grossProfitMargin.getCost(), BigDecimal.ZERO));
      margin.setExcludingTaxAmount(
          ObjectUtils.defaultIfNull(grossProfitMargin.getExcludingTaxAmount(), BigDecimal.ZERO));
      margin.setGrossProfit(
          ObjectUtils.defaultIfNull(grossProfitMargin.getGrossProfit(), BigDecimal.ZERO));
      margin.setMargin(ObjectUtils.defaultIfNull(grossProfitMargin.getGrossProfitMargin(), 0d));
      margin.setStatus("1");
      // 最后修改人和修改时间
      // margin.setUpdateBy(order.get);
      // margin.setUpdateTime(updateTime);

      wtwMargin.setAmount(grossProfitMargin.getAmount());
      wtwMargin.setCode(StringUtils.trimToEmpty(grossProfitMargin.getCode()));
      wtwMargin.setName(StringUtils.trimToEmpty(grossProfitMargin.getName()));
      wtwMargin.setCost(ObjectUtils.defaultIfNull(grossProfitMargin.getWtwCost(), BigDecimal.ZERO));
      wtwMargin.setExcludingTaxAmount(
          ObjectUtils.defaultIfNull(grossProfitMargin.getExcludingTaxAmount(), BigDecimal.ZERO));
      wtwMargin.setGrossProfit(
          ObjectUtils.defaultIfNull(grossProfitMargin.getWtwGrossProfit(), BigDecimal.ZERO));
      wtwMargin
          .setMargin(ObjectUtils.defaultIfNull(grossProfitMargin.getWtwGrossProfitMargin(), 0d));
      wtwMargin.setStatus("1");
      // 最后修改人和修改时间
      // wtwMargin.setUpdateBy(order.get);
      // wtwMargin.setUpdateTime(updateTime);
    }

    // Call the bpm interface to start the order approval process
    try {
      String json = new ObjectMapper().writeValueAsString(bpmOrder);
      bpmService.callSendProcess(json);
    } catch (Exception e) {
      logger.error("提交 BPM 失败", e);
      throw new RuntimeException("提交 BPM 失败 - " + e.getMessage());
    }
  }

  /**
   * 更新BPM审批状态和折扣
   * 
   * @param user
   * @param sequenceNumber
   * @param status
   * @param bodyDiscount 格式48.0，除了非标折扣订单，其他都为0
   * @param unitDiscount 格式48.0，除了非标折扣订单，其他都为0
   * @throws Exception
   */
  @Transactional
  public void updateBpmStatus(String user, String sequenceNumber, String status,
      Double bodyDiscount, Double unitDiscount, List<Map<String, Object>> approvalItems) throws Exception {
    if (StringUtils.isEmpty(sequenceNumber)) {
      throw new RuntimeException("sequenceNumber is empty.");
    }
    OrderQuery query = new OrderQuery();
    query.setLast(true);
    query.setIncludeDetail(true);
    query.setSequenceNumber(sequenceNumber);
    List<OrderDto> orders = this.findOrders(query).getList();
    OrderDto orderDto = orders.get(0);

    // 订单审批历史
    BpmDicision bpmDicision = new BpmDicision();
    bpmDicision.setOrderInfoId(orderDto.getId());
    bpmDicision.setIsPassed(status.equals("1") ? 1 : 0);

    String orderStatus = orderDto.getStatus();
    // 如果定状态已经是bpm审批通过，而存在未审批通过的特批发货申请，则修改特批发货申请状态
    if (OrderDto.ORDER_STATUS_APPROVED_UPDATE.equals(orderStatus)
        || OrderDto.ORDER_STATUS_APPROVED.equals(orderStatus)) {
      SpecialOrderApplication specialOrderApplication =
          specialOrderApplicationMapper.findByOrderInfo(orderDto.getId());
      if (specialOrderApplication != null && specialOrderApplication.getApplyStatus().equals(1)) {
        updateBpmUrgenDeliveryStatus(user, status, bpmDicision, specialOrderApplication);
        bpmDicision.setRemark("特批发货申请审批。sequenceNumber: " + orderDto.getSequenceNumber() );
      }
    }  else {
      if (status.equals("1")) {
        // 审批通过
        if (orderDto.getVersionNum() > 1) {
          status = OrderDto.ORDER_STATUS_APPROVED_UPDATE;
        } else {
          status = OrderDto.ORDER_STATUS_APPROVED;
        }
  
        // 经销商非标准折扣订单，并且柜体折扣或机组折扣在bpm审批时被修改，并且不是长期折扣
        if (orderDto.getStOrderType().equals("2") 
            && (orderDto.getBodyDiscount() != ObjectUtils.defaultIfNull(bodyDiscount, orderDto.getBodyDiscount())
                || orderDto.getMainDiscount() != ObjectUtils.defaultIfNull(unitDiscount, orderDto.getMainDiscount())
                || approvalItems != null) ) {
          orderDto.setStatus(status);
          orderDto.setUpdater(user);
          // 转换折扣格式为小数，用于计算
          convertDiscountToDecimal(orderDto);
          List<ItemDto> items = orderDto.getItems();
          
          // 修改订单行项目折扣，重新计算订单毛利率
          if (approvalItems != null) {
//            && (ObjectUtils.defaultIfNull(orderDto.getIsLongterm(), 0) != 1)
//            && orderDto.getIsSpecial() != 1) { // 非统一折扣，长期折扣，需要单独处理
            // 非统一折扣，长期折扣
            // 更新行项目的discount
            updateBpmItemDiscount2(orderDto, approvalItems);
            bpmDicision.setRemark("经销商非标折扣长期折扣非统一折扣审批。sequenceNumber: " + orderDto.getSequenceNumber() + ", " + new ObjectMapper().writeValueAsString(approvalItems));
          } else {
            // 修改订单行项目折扣，重新计算订单毛利率
            // 转换折扣格式为小数，用于计算
            double tmpbodyDiscount = bodyDiscount / 100;
            double tmpunitDiscount = unitDiscount / 100;
            orderDto.setApprovedBodyDiscount(tmpbodyDiscount);
            orderDto.setBodyDiscount(tmpbodyDiscount);
            orderDto.setApprovedMainDiscount(tmpunitDiscount);
            orderDto.setMainDiscount(tmpunitDiscount);
            // 更新行项目的discount
            updateBpmItemDicount(orderDto, tmpbodyDiscount, tmpunitDiscount);
            bpmDicision.setRemark("经销商非标折扣审批。sequenceNumber: " + orderDto.getSequenceNumber() + ",bodyDiscount: " + bodyDiscount + ", unitDiscount: " + unitDiscount);
          }
          // 计算合并折扣和行项目金额
          calcMergeDiscount(orderDto);
  
          // calculate gross profit margin
          List<MaterialGroups> margin = grossProfitMarginService.calculate(orderDto);
          orderDto.setGrossProfitMargin(new ObjectMapper().writeValueAsString(margin));
  
          OrderInfo orderInfo = new OrderInfo();
          BeanUtils.copyProperties(orderInfo, orderDto);
  //        Order order = orderMapper.findById(orderInfo.getOrderId());
          orderInfoMapper.update(orderInfo);
        } else {
          orderInfoMapper.updateStatus(orderDto.getId(), user, status, null, null,
              ObjectUtils.defaultIfNull(orderDto.getBodyDiscount() / 100, 0D), ObjectUtils.defaultIfNull(orderDto.getMainDiscount() / 100, 0D));
        }
      } else {
        // 审批拒绝
        status = OrderDto.ORDER_STATUS_MANAGER; // OrderDto.ORDER_STATUS_REJECT_BPM;
        orderInfoMapper.updateStatus(orderDto.getId(), user, status, null, null, null, null);
      }
      bpmDicision.setBodyDiscount(ObjectUtils.defaultIfNull(orderDto.getBodyDiscount(), 0D));
      bpmDicision.setMainDiccount(ObjectUtils.defaultIfNull(orderDto.getMainDiscount(), 0D));
      bpmDicision.setApprovedBodyDiscount(ObjectUtils.defaultIfNull(bodyDiscount, 0D));
      bpmDicision.setApprovedMainDiscount(ObjectUtils.defaultIfNull(unitDiscount, 0D));
    }
    
    // save approval log
    dicisionMapper.insert(bpmDicision);

    // send mail
    try {
      String to = "";
      to = userMapper.findByLoginName(orderDto.getSalesCode()).getUserMail();
      to += ";" + userMapper.findByLoginName(orderDto.getContractManager()).getUserMail();
      if (to.length() == 0) {
        logger.error("发送邮件失败！没有找到对应的用户");
        return;
      }
      sendApproveNoticeMail(orderDto, to);
    } catch (Exception e) {
      logger.error("发送邮件失败", e);
    }
  }

  private void updateBpmItemDiscount2(OrderDto order, List<Map<String, Object>> approvalItems) throws IllegalAccessException, InvocationTargetException {
    List<ItemDto> items = order.getItems();
    for (ItemDto itemDto : items) {
      // 特殊物料，只合计购销明细金额
      if (Constant.specialMaterials.contains(itemDto.getMaterialCode())) {
        continue;
      }
      String rowNum = String.valueOf(itemDto.getRowNum());
      for (Map map : approvalItems) {
        String lineNum = (String)map.get("lineNum");
        Double discount = (Double)map.get("discount") / 100;
        if (lineNum.equals(rowNum)) {
          if (itemDto.getDiscount() == discount.doubleValue()) {
            break;
          }
          itemDto.setDiscount(discount);
          // 非免费类别计算实卖价
          itemDto.setActualPrice(itemDto.getRetailPrice() * discount);
          itemDto.setOptionalActualPrice(ObjectUtils.defaultIfNull(itemDto.getOptionalRetailPrice(), 0D) * discount);
          // 合计金额、合计价，可以不用，其他地方都是以单价来计算
//                  itemDto.setActualAmount(itemDto.getActualPrice() * itemDto.getQuantity());
          
          Item item = new Item();
          BeanUtils.copyProperties(item, itemDto);
          item.setOrderInfoId(order.getId());
          itemMapper.update(item);
        }
      }
    }
  }

  /**
   * 更新紧急发货申请bpm审批状态
   * 
   * @param user
   * @param status
   * @param bpmDicision
   * @param specialOrderApplication
   */
  private void updateBpmUrgenDeliveryStatus(String user, String status, BpmDicision bpmDicision,
      SpecialOrderApplication specialOrderApplication) {
    if (status.equals("1")) {
      // 审批通过
      specialOrderApplication.setApplyStatus(2);
    } else {
      // 审批拒绝
      specialOrderApplication.setApplyStatus(3);
    }

    specialOrderApplication.setApprovalTime(new Date());
    specialOrderApplication.setApprover(user);

    specialOrderApplicationMapper.update(specialOrderApplication);
    
    bpmDicision.setBodyDiscount(-1D);
    bpmDicision.setMainDiccount(-1D);
    bpmDicision.setApprovedBodyDiscount(-1D);
    bpmDicision.setApprovedMainDiscount(-1D);
  }

  /**
   * 经销商非标折扣订单计算合计金额和折扣
   * @param orderDto
   */
  private void calcMergeDiscount(OrderDto orderDto) {
    // 行项目折扣金额合计
    double itemsAmount = 0;
    double itemsDicountAmount = 0;
    double itemsNonDicountAmount = 0;
    
    List<ItemDto> items = orderDto.getItems();
    // 行项目折扣前金额合计
    for (ItemDto itemDto : items) {
      String mcode = itemDto.getMaterialCode();
      // 取消状态的行项目不在累计范围
      if (itemDto.getItemStatus().equals("Z2")) {
        continue;
      }

      String category = itemDto.getItemCategory();
      // 退货
      double flag = Constant.returnCategorys.contains(category) ? -1 : 1;
      // 特殊物料，只合计购销明细金额
      if (Constant.specialMaterials.contains(mcode)) {
        // 退货
        itemsAmount += flag * itemDto.getActualPrice() * itemDto.getQuantity();
      } else {
        itemsAmount += flag * (itemDto.getActualPrice() + itemDto.getOptionalActualPrice() + itemDto.getB2cEstimatedPrice()) * itemDto.getQuantity();
        itemsDicountAmount += flag * (itemDto.getActualPrice() + itemDto.getOptionalActualPrice() + itemDto.getB2cEstimatedPrice()) * itemDto.getQuantity();
        itemsNonDicountAmount += flag * (itemDto.getRetailPrice() + ObjectUtils.defaultIfNull(itemDto.getOptionalRetailPrice(), 0D) + itemDto.getB2cEstimatedPrice()) * itemDto.getQuantity();
      }
    }
    // 凭证货币
    orderDto.setItemsAmount(itemsAmount / orderDto.getCurrencyExchange());
    // 凭证货币
    orderDto.setContractValue(itemsAmount / orderDto.getCurrencyExchange());
    orderDto.setContractRmbValue(itemsAmount);
    // merge discount
    if (itemsNonDicountAmount == 0) {
      orderDto.setDiscount(1D);
    } else {
      orderDto.setDiscount(BigDecimal.valueOf(itemsDicountAmount / itemsNonDicountAmount)
          .setScale(4, RoundingMode.HALF_UP).doubleValue());
    }
  }

  /**
   * 根据BPM审批结果更新行项目的折扣
   * 
   * @param items
   * @param bodyDiscount
   * @param unitDiscount
   * @throws IllegalAccessException
   * @throws InvocationTargetException
   */
  private void updateBpmItemDicount(OrderDto order, Double bodyDiscount, Double unitDiscount)
      throws IllegalAccessException, InvocationTargetException {
    List<ItemDto> items = order.getItems();
    for (ItemDto itemDto : items) {
      // 特殊物料，只合计购销明细金额
      if (Constant.specialMaterials.contains(itemDto.getMaterialCode())) {
        continue;
      }
      // 取消状态的行项目不在累计范围
      if (itemDto.getItemStatus().equals("Z2")) {
        continue;
      }
      String stGroup = itemDto.getStMaterialGroupCode();
      if (stGroup.equals("T101") || stGroup.equals("T102")) {
        // 机柜
        if (stGroup.equals("T101")) {
          itemDto.setDiscount(bodyDiscount);
        }
        // 机组
        if (itemDto.getStMaterialGroupCode().equals("T102")) {
          itemDto.setDiscount(unitDiscount);
        }
        double discount = itemDto.getDiscount();
        itemDto.setActualPrice(itemDto.getRetailPrice() * discount);
        itemDto.setOptionalActualPrice(ObjectUtils.defaultIfNull(itemDto.getOptionalRetailPrice(), 0D) * discount);
        // 合计金额、合计价，可以不用，其他地方都是以单价来计算
//        itemDto.setActualAmount(itemDto.getActualPrice() * itemDto.getQuantity());

        Item item = new Item();
        BeanUtils.copyProperties(item, itemDto);
        item.setOrderInfoId(order.getId());
        itemMapper.update(item);
      }
    }
  }

  /**
   * 修改报价单报价状态
   * 
   * @param user
   * @param orderInfoId
   * @param quoteStatus
   * @return
   */
  @Transactional
  public String updateQuoteStatus(String user, Integer orderInfoId, String quoteStatus) {
    OrderInfo orderInfo = orderInfoMapper.findById(orderInfoId);
    Order order = orderMapper.findById(orderInfo.getOrderId());
    order.setQuoteStatus(quoteStatus);
    orderMapper.update(order);
    return null;
  }

  /**
   * 查询订单在SAP的实时状态
   * 
   * @param contractNumber 合同号
   * @return
   */
  public SapOrderStatus getOrderSapStatus(String contractNumber) {
    return sapService.getOrderStatus(contractNumber);
  }

  /**
   * 修改订单合同号
   * @param orderInfoId
   * @param contractNumber
   */
  public void updateContractNumber(Integer orderInfoId, String contractNumber) {
    String tempContractNumber = StringUtils.trimToEmpty(contractNumber).toUpperCase();
    OrderInfo orderInfo = orderInfoMapper.findById(orderInfoId);
    checkContractNumber(orderInfo.getOrderId(), tempContractNumber);
    orderInfo.setContractNumber(tempContractNumber);
    orderInfoMapper.update(orderInfo);
  }

}
