from django.conf import settings
from django.db import models
from django.utils import timezone

class VkToken(models.Model):
    user_id = models.IntegerField()
    token = models.CharField(max_length=250)

    def __str__(self):
        return self.token

class Post(models.Model):
    user_id = models.IntegerField()
    title = models.CharField(max_length = 200)
    text = models.TextField()
    likes = models.IntegerField()