from __future__ import absolute_import, unicode_literals
from celery import shared_task
import os
from rest_framework.generics import get_object_or_404
from smm_backend.models import UserPost, VkToken
import requests
from django.contrib.auth.models import User
import datetime
from django.shortcuts import get_list_or_404


# @shared_task(name = "print_msg_with_name")
# def print_message(name, *args, **kwargs):
#   print("Celery is working!! {} have implemented it correctly.".format(name))

@shared_task(name = "post")
def add():
    queryset = UserPost.objects.all()
    data = datetime.datetime.now()
    obj = get_list_or_404(queryset)
    print("!!!!!!!!!FFFFFFFFFFFFFFF!!!!!!!!")
    print(obj)
    for post in obj:
        if (post.date_post.strftime("%Y-%M-%D %H:%M:%S") == data.strftime("%Y-%M-%D %H:%M:%S")):
            qq = VkToken.objects.all()
            obj = get_object_or_404(qq, user_id=post.user_id)
            t = obj.token
            r = requests.post("https://api.vk.com/method/wall.post?owner_id=-" + post.group_id + "&from_group=1&message=" + post.text + "&access_token=" + t + "&v=5.103")