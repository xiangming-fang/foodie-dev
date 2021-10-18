package indi.xm.controller.center;

import indi.xm.bo.center.CenterUserBO;
import indi.xm.enums.ConstantEnum;
import indi.xm.pojo.Users;
import indi.xm.resources.FileUpload;
import indi.xm.service.center.CenterUserService;
import indi.xm.utils.CookieUtils;
import indi.xm.utils.DateUtil;
import indi.xm.utils.JsonUtils;
import indi.xm.utils.XMJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: foodie-dev
 * @Package: indi.xm.controller.center
 * @ClassName: UserCenterController
 * @Author: albert.fang
 * @Description: 用户中心的个人信息
 * @Date: 2021/10/18 13:05
 */
@RestController
@Api(value = "用户中心的个人信息",tags = "用户中心的个人信息api")
@RequestMapping("userInfo")
public class UserCenterController {

    @Resource
    private CenterUserService centerUserService;

    @Resource
    private FileUpload fileUpload;

    @PostMapping("uploadFace")
    @ApiOperation(value = "用户头像修改",tags = "用户头像修改",httpMethod = "POST")
    public XMJSONResult uploadFace(@ApiParam(name = "userId",value = "用户id",required = true) @RequestParam String userId,
                                   @ApiParam(name = "file",value = "用户头像文件",required = true) @RequestParam MultipartFile file,
                               HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 1、定义头像保存的地址
        String fileSpace = fileUpload.getImageUserFaceLocation();

        // 2、在路径上位每一个用户增加一个userid，用于区分每个不同用户上传
        String uploadPathPrefix = File.separator + userId;

        String path = null;

        // 开始文件上传
        if (file != null){

            // 获得文件上传的文件名称
            String filename = file.getOriginalFilename();
            if (StringUtils.isNotBlank(filename)){

                // 文件重命名：保存方式 face-｛userid｝.png
                String[] fileNameArr = filename.split("\\.");

                // 获取文件的后缀名
                String suffix = fileNameArr[fileNameArr.length - 1];

                if (!suffix.equalsIgnoreCase("png") && !suffix.equalsIgnoreCase("jpg") && !suffix.equalsIgnoreCase("jpeg")){
                    return XMJSONResult.errorMsg("图片格式不正确");
                }

                // 文件名称重组 face-{userid}.png
                String newFileName = ConstantEnum.PREFIX_FACE.value + userId + "-" + System.currentTimeMillis() + "." + suffix;

                // 上传的头像最终保存的位置
                path = fileSpace + uploadPathPrefix + File.separator + newFileName;

                File outFile = new File(path);
                if (outFile.getParentFile() != null) {
                    // 创建文件夹
                    outFile.getParentFile().mkdirs();
                }

                // 文件输出保存到目录
                try (FileOutputStream fos = new FileOutputStream(outFile); InputStream is = file.getInputStream()) {
                    IOUtils.copy(is, fos);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else {
            return XMJSONResult.errorMsg("文件不能为空");
        }

        // 获取服务图片地址
        String imageServerUrl = fileUpload.getImageServerUrl();

        // 网络映射得到的本地图片地址
        // 通过网络访问本地静态资源，防止缓存，后面加个时间戳
        String finalPath = imageServerUrl + path + "?t=" + DateUtil.getCurrentDateString(DateUtil.DATETIME_PATTERN);

        Users users = centerUserService.updateUserFace(userId, finalPath);

        CookieUtils.setCookie(request,response,"user",JsonUtils.objectToJson(users),true);
        return XMJSONResult.ok(users);
    }

    /**
     * 根据userId更新用户信息
     *
     * @param userId
     * @return
     */
    @PostMapping("update")
    @ApiOperation(value = "更新用户信息",tags = "更新用户信息",httpMethod = "POST")
    public XMJSONResult update(@ApiParam(name = "userId",value = "用户id",required = true) @RequestParam String userId,
                               @RequestBody @Valid CenterUserBO centerUserBO,
                               BindingResult result,
                               HttpServletRequest request,
                               HttpServletResponse response){
        // 判断binddingresult是否包含错误的验证消息，如果有直接return
        if (result.hasErrors()) {
            Map<String, String> errors = getErrors(result);
            return XMJSONResult.errorMap(errors);
        }
        Users resUser = centerUserService.updateUserById(userId, centerUserBO);
        setNullProperty(resUser);
        // 刷新前端cookie
        CookieUtils.setCookie(request,response,"user", JsonUtils.objectToJson(resUser),true);

        // TODO 后续要改，增加令牌token，会整合进redis，分布式会话

        return XMJSONResult.ok();
    }

    private void setNullProperty(Users users){
        users.setPassword(null);
        users.setRealname(null);
        users.setEmail(null);
        users.setCreatedTime(null);
        users.setUpdatedTime(null);
        users.setBirthday(null);
    }

    /**
     * 通过hibernate的valid验证从前端传到后端的bo错误信息
     * @param bindResult
     * @return
     */
    private Map<String,String> getErrors(BindingResult bindResult){
        HashMap<String, String> errorInfoMap = new HashMap<>();
        List<FieldError> fieldErrors = bindResult.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            // 发生错误所对应的某一个属性
            String field = fieldError.getField();
            // 验证错误的信息
            String errorMsg = fieldError.getDefaultMessage();
            errorInfoMap.put(field,errorMsg);
        }
        return errorInfoMap;
    }
}
